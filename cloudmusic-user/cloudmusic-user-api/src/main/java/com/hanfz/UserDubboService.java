package com.hanfz;

import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.param.LoginParam;
import com.hanfz.pojo.param.RegisterParam;

/**
 * @Author Hanfz
 * @Description
 * @Date Created in 2022-08-30 0:04
 */
public interface UserDubboService {

    /**
     * 发送注册验证码
     *
     * @param phone
     * @return
     */
    ResponseData sendRegisterVerificationCode(String phone);

    /**
     * 用户注册
     *
     * @param registerParam
     * @return
     */
    ResponseData register(RegisterParam registerParam);

    /**
     * 用户登录
     *
     * @param loginParam
     * @return
     */
    ResponseData<String> login(LoginParam loginParam);

}
