package com.hanfz.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Author Linuyx
 * @Description 注册请求体
 * @Date Created in 2022-05-11 15:32
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParam {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "0?(13|14|15|17|18|19)[0-9]{9}", message = "请输入合法手机号")
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "密码长度为8-16位")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "请输入6位验证码")
    private String verificationCode;

    /**
     * 随机盐
     */
    private String salt;

}
