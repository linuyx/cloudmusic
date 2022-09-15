package com.hanfz.service;

import com.hanfz.pojo.param.role.EditParam;
import com.hanfz.pojo.param.role.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.role.RoleVO;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-26 20:26
 */
public interface RoleService {

    /**
     * 查询所有角色
     *
     * @return
     */
    List<RoleVO> getAllRoles();

    /**
     * 多条件查询角色
     *
     * @param selectParam
     * @return
     */
    PageData<RoleVO> getRoles(SelectParam selectParam);

    /**
     * 删除角色
     *
     * @param ids
     */
    void deleteRoles(List<Long> ids);

    /**
     * 新增角色
     *
     * @param editParam
     */
    void insertRole(EditParam editParam);

    /**
     * 更新角色信息
     *
     * @param editParam
     */
    void updateRole(EditParam editParam);

}
