package com.hanfz.service.impl;

import com.hanfz.SnowFlakeDubboService;
import com.hanfz.dao.mapper.ResourceMapper;
import com.hanfz.dao.mapper.RoleResourceMapper;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.resource.EditParam;
import com.hanfz.pojo.param.resource.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.resource.ResourceBaseVO;
import com.hanfz.pojo.vo.resource.ResourceVO;
import com.hanfz.service.ResourceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-24 20:07
 */

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @DubboReference
    private SnowFlakeDubboService snowFlakeService;

    @Autowired
    private RoleResourceMapper roleResourceMapper;


    /**
     * 多条件查询资源
     *
     * @param selectParam
     * @return
     */
    @Override
    public PageData<ResourceVO> getResources(SelectParam selectParam) {
        //统计总数
        Long totalCount = resourceMapper.countResource(selectParam);

        if(totalCount == 0){
            return PageData.<ResourceVO>builder()
                    .totalCount(0L)
                    .build();
        }

        //多条件查询
        selectParam.setCurrent((selectParam.getCurrent() - 1) * selectParam.getSize());
        List<ResourceVO> resourceList = resourceMapper.getResources(selectParam);

        //构建分页返回对象
        return PageData.<ResourceVO>builder()
                .totalCount(totalCount)
                .data(resourceList)
                .build();
    }

    /**
     * 查询所有资源
     *
     * @return
     */
    @Override
    public List<ResourceBaseVO> getAllResources() {
        return resourceMapper.getAllResources();
    }

    /**
     * 根据角色id查询资源
     *
     * @return
     */
    @Override
    public List<ResourceBaseVO> getResourcesByRoleId(Long roleId) {
        return resourceMapper.getResourcesByRoleId(roleId);
    }

    /**
     * 新增资源
     *
     * @param editParam
     */
    @Override
    public void insertResource(EditParam editParam) {
        if(resourceMapper.existResourceEdit(editParam) != null){
            throw new GlobalException("该资源已存在");
        }

        //获取分布式id
        Long resourceId = snowFlakeService.getSnowFlake().getData();
        editParam.setId(resourceId);

        try {
            //新增资源
            resourceMapper.insertResource(editParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该资源已存在");
        }

    }

    /**
     * 更新资源
     *
     * @param editParam
     */
    @Override
    public void updateResource(EditParam editParam) {
        if(resourceMapper.existResourceEdit(editParam) != null){
            throw new GlobalException("该资源已存在");
        }

        try {
            //更新资源
            resourceMapper.updateResource(editParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该资源已存在");
        }

    }

    /**
     * 删除资源
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteResources(List<Long> ids) {
        //删除资源
        resourceMapper.deleteResources(ids);
        //根据资源id删除角色、资源对应关系
        roleResourceMapper.deleteRoleResourceByResourceIds(ids);
    }

}
