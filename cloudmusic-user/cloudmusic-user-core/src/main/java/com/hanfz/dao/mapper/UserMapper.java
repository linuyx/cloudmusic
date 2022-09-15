package com.hanfz.dao.mapper;

import com.hanfz.pojo.entity.UserEntity;
import com.hanfz.pojo.param.RegisterParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-22 20:56
 */

@Mapper
public interface UserMapper {

    /**
     * 根据手机号查询用户
     *
     * @param phone
     * @return
     */
    UserEntity getUserByPhone(@Param("phone") String phone);

    /**
     * 新增用户
     *
     * @param registerParam 注册用户请求体
     */
    void insertUser(RegisterParam registerParam);

}
