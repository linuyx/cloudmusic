package com.hanfz.exception;

import com.hanfz.enums.ResponseEnum;
import lombok.Data;

/**
 * @Author Linuyx
 * @Description 全局业务异常类
 * @Date Created in 2021-04-08 15:20
 */

@Data
public class GlobalException extends RuntimeException{

    private Integer code;

    public GlobalException(String message){
        super(message);
        this.code = ResponseEnum.CUSTOM_ERROR.getCode();
    }

    public GlobalException(Integer code, String message){
        super(message);
        this.code = code;
    }

}
