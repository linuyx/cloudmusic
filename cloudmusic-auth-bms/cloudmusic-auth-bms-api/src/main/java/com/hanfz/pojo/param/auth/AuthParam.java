package com.hanfz.pojo.param.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-10-14 16:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AuthParam implements Serializable {

    /**
     * token
     */
    @NotBlank(message = "token不能为空")
    private String token;

    /**
     * 请求路径
     */
    @NotBlank(message = "请求路径不能为空")
    private String url;

    /**
     * 请求方式
     */
    @NotBlank(message = "请求方式不能为空")
    private String method;

}
