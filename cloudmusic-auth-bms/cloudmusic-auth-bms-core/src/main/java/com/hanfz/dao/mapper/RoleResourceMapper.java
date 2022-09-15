package com.hanfz.dao.mapper;

import com.hanfz.pojo.entity.RoleResourceEntity;
import com.hanfz.pojo.param.OtmParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-29 21:49
 */

@Mapper
public interface RoleResourceMapper {

    /**
     * 根据资源id获取角色、资源映射
     *
     * @param resourceId
     * @return
     */
    RoleResourceEntity getRoleResourceByResourceId(@Param("resourceId") Long resourceId);

    /**
     * 新增角色、资源映射
     *
     * @param id         id
     * @param roleId     角色id
     * @param resourceId 资源id
     */
    void insertRoleResource(@Param("id") Long id,
                            @Param("roleId") Long roleId,
                            @Param("resourceId") Long resourceId);

    /**
     * 新增角色、资源映射
     *
     * @param otmParams
     */
    void insertRoleResources(@Param("otmParams") List<OtmParam> otmParams);

    /**
     * 更新角色、资源映射
     *
     * @param id         id
     * @param roleId     角色id
     * @param resourceId 资源id
     */
    void updateRoleResource(@Param("id") Long id,
                            @Param("roleId") Long roleId,
                            @Param("resourceId") Long resourceId);

    /**
     * 根据角色id集合删除角色、资源对应关系-删除角色时删除角色、资源对应关系
     *
     * @param roleIds
     */
    void deleteRoleResourceByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据资源id集合删除角色、资源对应关系-删除资源时删除角色、资源对应关系
     *
     * @param resourceIds
     */
    void deleteRoleResourceByResourceIds(@Param("resourceIds") List<Long> resourceIds);

}
