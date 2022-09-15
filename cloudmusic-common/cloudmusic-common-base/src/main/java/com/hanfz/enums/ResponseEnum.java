package com.hanfz.enums;

/**
 * @Author Linuyx
 * @Description 全局状态码枚举类
 * @Date Created in 2021-04-08 15:35
 */
public enum ResponseEnum {

    /**
     * 全局状态码
     */
    SUCCESS(200, "success"),
    SYSTEM_ERROR(100,"服务器错误"),
    CUSTOM_ERROR(101, "自定义业务异常"),
    TOKEN_NOTNULL_ERROR(102,"token不能为空"),
    TOKEN_SIGNATURE_ERROR(103, "token签名不正确"),
    TOKEN_EXPIRED_ERROR(104,"token已过期"),
    AUTH_ERROR(105,"未经授权,无法访问"),
    PARAM_ERROR(106,"参数校验错误"),
    VERIFICATION_CODE_ERROR(107, "验证码错误");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String message;

    ResponseEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
