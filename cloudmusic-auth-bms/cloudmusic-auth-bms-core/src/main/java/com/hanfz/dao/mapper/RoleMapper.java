package com.hanfz.dao.mapper;

import com.hanfz.pojo.param.role.EditParam;
import com.hanfz.pojo.param.role.SelectParam;
import com.hanfz.pojo.vo.role.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-10-11 16:20
 */

@Mapper
public interface RoleMapper {

    /**
     * 根据角色名称查询角色id
     *
     * @param name 角色名称
     * @return
     */
    Long getIdByName(@Param("name") String name);

    /**
     * 根据角色名称集合查询角色id
     *
     * @param names 角色名称集合
     * @return
     */
    List<Long> getIdByNames(@Param("names") List<String> names);

    /**
     * 根据id判断角色是否存在
     *
     * @param id
     * @return
     */
    Integer existRoleById(@Param("id") Long id);

    /**
     * 判断集合中是否存在超级管理员
     *
     * @param roleIds
     * @param superAdminName
     *
     * @return
     */
    Integer existSuperAdmin(@Param("roleIds") List<Long> roleIds, @Param("superAdminName") String superAdminName);

    /**
     * 根据名称判断是否存在角色-用于编辑时
     *
     * @param editParam
     * @return
     */
    Integer existRoleEdit(EditParam editParam);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<RoleVO> getAllRoles();

    /**
     * 多条件统计总数
     *
     * @param selectParam
     * @return
     */
    Long countRole(SelectParam selectParam);

    /**
     * 多条件查询总数
     *
     * @param selectParam
     * @return
     */
    List<RoleVO> getRoles(SelectParam selectParam);

    /**
     * 新增角色
     *
     * @param editParam
     */
    void insertRole(EditParam editParam);

    /**
     * 更新角色
     *
     * @param editParam
     */
    void updateRole(EditParam editParam);

    /**
     * 删除角色
     *
     * @param ids
     */
    void deleteRoles(@Param("ids") List<Long> ids);

}
