package com.hanfz.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-10-11 16:30
 */

@Mapper
public interface UserRoleMapper {

    /**
     * 给用户赋予角色
     *
     * @param id
     * @param userId 用户id
     * @param roleId 角色id
     */
    void insertUserRole(@Param("id") Long id,
                           @Param("userId") Long userId,
                           @Param("roleId") Long roleId);

    /**
     * 根据用户id获取账号角色
     *
     * @param userId 用户id
     * @return
     */
    List<String> getRoleByUserId(@Param("userId") Long userId);
}
