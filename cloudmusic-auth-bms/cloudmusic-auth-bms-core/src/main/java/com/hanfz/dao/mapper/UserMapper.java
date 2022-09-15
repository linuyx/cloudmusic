package com.hanfz.dao.mapper;

import com.hanfz.pojo.entity.UserEntity;
import com.hanfz.pojo.param.auth.RegisterParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 15:34
 */

@Mapper
public interface UserMapper {

    /**
     * 根据用户名判断账户是否存在
     *
     * @param username 用户名
     * @return
     */
    Integer existUser(@Param("username") String username);

    /**
     * 根据用户名查询账号信息
     *
     * @param username
     * @return
     */
    UserEntity getUserByUsername(@Param("username") String username);

    /**
     * 新增账号
     *
     * @param registerParam 新增账号请求体
     */
    void insertUser(RegisterParam registerParam);

}
