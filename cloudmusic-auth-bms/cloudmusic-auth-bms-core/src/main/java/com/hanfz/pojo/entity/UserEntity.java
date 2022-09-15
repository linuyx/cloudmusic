package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @Author Linuyx
 * @Description account表
 * @Date Created in 2021-9-17 16:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    /**
     * 用户名(6-16位)
     */
    private String username;

    /**
     * 密码(MD5,8-16位)
     */
    private String password;

    /**
     * 随机盐
     */
    private String salt;

    /**
     * 名称
     */
    private String name;

    /**
     * 性别, 1 男, 0 女
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String image;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

}

