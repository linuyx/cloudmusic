package com.hanfz.controller;

import com.hanfz.AuthDubboService;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.auth.AuthParam;
import com.hanfz.pojo.param.auth.LoginParam;
import com.hanfz.pojo.param.auth.RegisterParam;
import com.hanfz.pojo.request.RequestDataBms;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.service.AuthService;
import io.jsonwebtoken.SignatureException;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-15 21:03
 */

@DubboService
@RestController
@RequestMapping("/auth-bms")
public class AuthController implements AuthDubboService {

    @Autowired
    private AuthService authService;


    @Override
    @PostMapping(value = "/register", name = "新增管理员")
    public ResponseData register(@Valid @RequestBody RegisterParam registerParam) {
        authService.register(registerParam);
        return ResponseData.success("添加成功");
    }

    @Override
    @PostMapping(value = "/login", name = "登录")
    public ResponseData<String> login(@Valid @RequestBody LoginParam loginParam) {
        return ResponseData.success("登录成功", authService.login(loginParam));
    }

    @Override
    @PostMapping(value = "/auth", name = "判断用户是否有当前资源权限")
    public ResponseData<RequestDataBms> auth(@Valid @RequestBody AuthParam authParam) {
        return ResponseData.success("用户有当前资源权限", authService.auth(authParam));
    }

}
