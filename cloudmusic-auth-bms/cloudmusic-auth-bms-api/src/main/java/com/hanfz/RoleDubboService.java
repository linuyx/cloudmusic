package com.hanfz;

import com.hanfz.pojo.param.role.EditParam;
import com.hanfz.pojo.param.role.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.role.RoleVO;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-08-21 15:35
 */
public interface RoleDubboService {

    /**
     * 查询所有角色
     *
     * @return
     */
    ResponseData<List<RoleVO>> getAllRoles();

    /**
     * 多条件查询角色
     *
     * @param selectParam
     * @return
     */
    ResponseData<PageData<RoleVO>> getRoles(SelectParam selectParam);

    /**
     * 新增角色
     *
     * @param editParam
     * @return
     */
    ResponseData insertRole(EditParam editParam);

    /**
     * 更新角色
     *
     * @param editParam
     * @return
     */
    ResponseData updateRole(EditParam editParam);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    ResponseData deleteRoles(List<Long> ids);

}
