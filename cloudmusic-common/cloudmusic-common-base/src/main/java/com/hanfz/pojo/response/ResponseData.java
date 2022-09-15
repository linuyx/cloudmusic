package com.hanfz.pojo.response;

import com.hanfz.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description 数据统一返回类
 * @Date Created in 2021-04-07 22:21
 */

@Data
public class ResponseData<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public ResponseData() {

    }

    public ResponseData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseData<T> success(String message) {
        return new ResponseData<T>(ResponseEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> ResponseData<T> success(String message, T data) {
        return new ResponseData<T>(ResponseEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> ResponseData<T> fail(String message) {
        return new ResponseData<T>(ResponseEnum.CUSTOM_ERROR.getCode(), message, null);
    }

    public static <T> ResponseData<T> fail(Integer code, String message) {
        return new ResponseData<T>(code, message, null);
    }

}
