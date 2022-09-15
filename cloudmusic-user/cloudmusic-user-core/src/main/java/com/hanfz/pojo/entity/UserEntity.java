package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-05-17 16:29
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
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
     * 性别
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String imageUrl;

    /**
     * 邮箱
     */
    private String email;

}
