package com.hanfz.pojo.param.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-10-11 21:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginParam implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 16, message = "用户名长度必须为6-16位")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "密码长度必须为8-16位")
    private String password;

}
