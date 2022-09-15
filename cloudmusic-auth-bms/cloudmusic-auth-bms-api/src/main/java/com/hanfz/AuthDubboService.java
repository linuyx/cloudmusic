package com.hanfz;

import com.hanfz.pojo.param.auth.AuthParam;
import com.hanfz.pojo.param.auth.LoginParam;
import com.hanfz.pojo.param.auth.RegisterParam;
import com.hanfz.pojo.request.RequestDataBms;
import com.hanfz.pojo.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-08-21 15:34
 */
public interface AuthDubboService{

    /**
     * 新增管理员
     *
     * @param registerParam
     * @return
     */
    ResponseData register(RegisterParam registerParam);

    /**
     * 登录
     *
     * @param loginParam
     * @return
     */
    ResponseData<String> login(LoginParam loginParam);

    /**
     * 判断用户是否有当前资源权限
     *
     * @param authParam
     * @return
     */
    ResponseData<RequestDataBms> auth(AuthParam authParam);

}
