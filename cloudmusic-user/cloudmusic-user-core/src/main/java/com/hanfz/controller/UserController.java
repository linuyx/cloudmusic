package com.hanfz.controller;

import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.service.UserService;
import com.hanfz.UserDubboService;
import com.hanfz.pojo.param.LoginParam;
import com.hanfz.pojo.param.RegisterParam;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.regex.Pattern;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-13 21:00
 */

@DubboService
@RestController
@RequestMapping("/users")
public class UserController implements UserDubboService {

    @Autowired
    private UserService userService;


    @Override
    @GetMapping(value = "/register-verification-code", name = "发送注册验证码")
    public ResponseData sendRegisterVerificationCode(String phone){
        //校验手机号是否合法
        authPhone(phone);

        userService.sendRegisterVerificationCode(phone);
        return ResponseData.success("验证码发送成功");
    }

    @Override
    @PostMapping(value = "/register", name = "用户注册")
    public ResponseData register(@Valid @RequestBody RegisterParam registerParam){
        userService.register(registerParam);
        return ResponseData.success("注册成功");
    }

    @Override
    @PostMapping(value = "/login", name = "用户登录")
    public ResponseData<String> login(@Valid @RequestBody LoginParam loginParam){
        return ResponseData.success("登录成功", userService.login(loginParam));
    }

    /**
     * 校验手机号是否合法
     *
     * @param phone 手机号
     */
    private void authPhone(String phone){
        if(!Pattern.matches("0?(13|14|15|17|18|19)[0-9]{9}", phone)){
            throw new GlobalException("请填写合法的手机号");
        }
    }

}
