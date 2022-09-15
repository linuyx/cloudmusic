package com.hanfz.exception;

import com.hanfz.enums.ResponseEnum;
import com.hanfz.pojo.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author Linuyx
 * @Description 全局业务异常处理类
 * @Date Created in 2021-04-08 15:22
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常
     *
     * @param ex GlobalException
     * @return 异常信息
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseData globalExceptionHandler(GlobalException ex) {
        log.warn("[业务异常] {code: {}, message: {}}", ex.getCode(), ex.getMessage());
        return ResponseData.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 参数校验异常
     *
     * @param ex BindException
     * @return 异常信息
     */
    @ExceptionHandler(BindException.class)
    public ResponseData bindExceptionHandler(BindException ex) {
        //获取所有异常
        List<ObjectError> allErrors = ex.getAllErrors();
        //拿到第一个异常
        ObjectError objectError = allErrors.get(0);
        //获取异常详细信息
        String message = objectError.getDefaultMessage();

        log.warn("[参数校验异常] {}", ex.toString());
        return ResponseData.fail(ResponseEnum.PARAM_ERROR.getCode(), message);
    }

    /**
     * token签名异常
     *
     * @param ex SignatureException
     * @return
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseData signatureExceptionHandler(SignatureException ex) {
        log.warn("[Jwt签名异常] {}", ex.toString());
        return ResponseData.fail(ResponseEnum.TOKEN_SIGNATURE_ERROR.getCode(),
                ResponseEnum.TOKEN_SIGNATURE_ERROR.getMessage());
    }

    /**
     * token过期异常
     *
     * @param ex ExpiredJwtException
     * @return
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseData expiredJwtExceptionHandler(ExpiredJwtException ex){
        log.warn("[Jwt过期异常] {}", ex.toString());
        return ResponseData.fail(ResponseEnum.TOKEN_EXPIRED_ERROR.getCode(),
                ResponseEnum.TOKEN_EXPIRED_ERROR.getMessage());
    }

    /**
     * 其他异常
     *
     * @param ex Exception
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseData exception(Exception ex) {
        log.error( "[系统异常] {}", ex.toString());
        return ResponseData.fail(ResponseEnum.SYSTEM_ERROR.getCode(),
                ResponseEnum.SYSTEM_ERROR.getMessage());
    }

}
