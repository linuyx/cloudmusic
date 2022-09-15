package com.hanfz.service;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-05-07 16:03
 */
public interface VerificationCodeService {

    /**
     * 发送手机验证码
     *
     * @param phone
     * @return
     */
    void sendPhoneVerificationCode(String phone);

    /**
     * 校验验证码是否正确
     *
     * @param phone             手机号
     * @param verificationCode  验证码
     */
    void authVerificationCode(String phone, String verificationCode);

}
