package com.hanfz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.hanfz.SnowFlakeDubboService;
import com.hanfz.dao.mapper.ResourceMapper;
import com.hanfz.dao.mapper.RoleMapper;
import com.hanfz.dao.mapper.RoleResourceMapper;
import com.hanfz.enums.EditEnum;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.OtmParam;
import com.hanfz.pojo.param.role.EditParam;
import com.hanfz.pojo.param.role.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.role.RoleVO;
import com.hanfz.service.RoleService;
import com.hanfz.utils.OtmUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-26 20:28
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @DubboReference
    private SnowFlakeDubboService snowFlakeService;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 超级管理员
     */
    private final String SUPER_ADMIN = "super_admin";


    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<RoleVO> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    /**
     * 多条件查询角色
     *
     * @param selectParam
     * @return
     */
    @Override
    public PageData<RoleVO> getRoles(SelectParam selectParam) {
        //统计总数
        Long totalCount = roleMapper.countRole(selectParam);

        if(totalCount == 0){
            return PageData.<RoleVO>builder()
                    .totalCount(totalCount)
                    .build();
        }

        //多条件查询
        selectParam.setCurrent((selectParam.getCurrent() - 1) * selectParam.getSize());
        List<RoleVO> roleList = roleMapper.getRoles(selectParam);

        //构建分页返回对象
        return PageData.<RoleVO>builder()
                .totalCount(totalCount)
                .data(roleList)
                .build();
    }

    /**
     * 新增角色
     *
     * @param editParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRole(EditParam editParam) {
        editRole(editParam, EditEnum.INSERT);
    }

    /**
     * 更新角色信息
     *
     * @param editParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(EditParam editParam) {
        editRole(editParam, EditEnum.UPDATE);
    }

    /**
     * 编辑角色
     *
     * @param editParam 编辑角色请求体
     * @param editEnum 编辑枚举
     */
    @Transactional(rollbackFor = Exception.class)
    public void editRole(EditParam editParam, EditEnum editEnum){
        //根据名称判断是否存在角色
        if(roleMapper.existRoleEdit(editParam) != null){
            throw new GlobalException("角色已存在");
        }

        //判断资源集合中是否有资源不存在
        if(CollUtil.isNotEmpty(editParam.getResourceIds())){
            Integer resourceNumber = resourceMapper.countResourceByIds(editParam.getResourceIds());
            if(resourceNumber != editParam.getResourceIds().size()){
                throw new GlobalException("有资源不存在");
            }
        }

        if(editEnum == EditEnum.INSERT){
            //获取分布式id
            int n = editParam.getResourceIds().size();
            List<Long> ids = snowFlakeService.getSnowFlakes(n + 1).getData();
            editParam.setId(ids.get(0));

            try {
                //新增角色
                roleMapper.insertRole(editParam);
            }catch (DuplicateKeyException ex) {
                throw new GlobalException("该角色已存在");
            }

            //如果资源id集合不为空,新增对应关系
            if(CollUtil.isNotEmpty(editParam.getResourceIds())){
                //构建角色、资源对应关系对象
                List<OtmParam> otmParams = OtmUtils.getOtms(
                        ids.subList(1, ids.size()), ids.get(0), editParam.getResourceIds());

                //新增角色、资源对应关系
                roleResourceMapper.insertRoleResources(otmParams);
            }
        }else if(editEnum == EditEnum.UPDATE){
            try {
                //更新角色信息
                roleMapper.updateRole(editParam);
            }catch (DuplicateKeyException ex) {
                throw new GlobalException("该角色已存在");
            }

            //如果资源id集合不为空,新增对应关系
            if(CollUtil.isNotEmpty(editParam.getResourceIds())){
                //根据角色id删除角色、资源映射关系
                roleResourceMapper.deleteRoleResourceByRoleIds(List.of(editParam.getId()));

                //获取id集合
                List<Long> ids = snowFlakeService.getSnowFlakes(editParam.getResourceIds().size()).getData();
                //构建角色、资源对应关系对象
                List<OtmParam> otmParams = OtmUtils.getOtms(ids, editParam.getId(), editParam.getResourceIds());
                //新增角色、资源对应关系
                roleResourceMapper.insertRoleResources(otmParams);
            }
        }
    }

    /**
     * 删除角色
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(List<Long> ids) {
        if(roleMapper.existSuperAdmin(ids, SUPER_ADMIN) != null){
            throw new GlobalException(SUPER_ADMIN + "不能删除");
        }

        //删除角色
        roleMapper.deleteRoles(ids);
        //删除角色、资源对应关系
        roleResourceMapper.deleteRoleResourceByRoleIds(ids);
    }

}
