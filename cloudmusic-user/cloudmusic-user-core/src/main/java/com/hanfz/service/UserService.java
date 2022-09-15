package com.hanfz.service;

import com.hanfz.pojo.param.LoginParam;
import com.hanfz.pojo.param.RegisterParam;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 19:52
 */
public interface UserService {

    /**
     * 发送注册验证码
     *
     * @param phone 手机号
     * @return
     */
    void sendRegisterVerificationCode(String phone);

    /**
     * 用户注册
     *
     * @param registerParam 注册请求体
     */
    void register(RegisterParam registerParam);

    /**
     * 用户登录
     *
     * @param loginParam 登录请求体
     * @return
     */
    String login(LoginParam loginParam);

}
