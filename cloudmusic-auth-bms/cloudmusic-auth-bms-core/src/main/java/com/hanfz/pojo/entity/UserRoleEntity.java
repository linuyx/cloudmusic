package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Linuyx
 * @Description account_role表
 * @Date Created in 2021-9-17 16:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserRoleEntity extends BaseEntity {

    /**
     * 账号id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

}

