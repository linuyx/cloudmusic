package com.hanfz;

import com.hanfz.pojo.response.ResponseData;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-08-18 0:20
 */
public interface VerificationCodeDubboService {

    /**
     * 发送手机验证码
     *
     * @param phone 手机号
     * @return
     */
    ResponseData sendPhoneVerificationCode(String phone);

    /**
     * 校验验证码是否正确
     *
     * @param phone 手机号
     * @param verificationCode 验证码
     * @return
     */
    ResponseData authVerificationCode(String phone, String verificationCode);

}
