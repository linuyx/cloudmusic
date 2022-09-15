package com.hanfz.service;

import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.auth.AuthParam;
import com.hanfz.pojo.param.auth.LoginParam;
import com.hanfz.pojo.param.auth.RegisterParam;
import com.hanfz.pojo.request.RequestDataBms;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 14:43
 */
public interface AuthService {

    /**
     * 用户注册和添加管理员
     *
     * @param registerParam 注册请求体
     */
    void register(RegisterParam registerParam);

    /**
     * 登录
     *
     * @param loginParam
     * @return token
     */
    String login(LoginParam loginParam);

    /**
     * 判断用户是否有当前资源权限
     *
     * @param authParam 请求数据请求体
     * @return 当前用户信息
     */
    RequestDataBms auth(AuthParam authParam);

}
