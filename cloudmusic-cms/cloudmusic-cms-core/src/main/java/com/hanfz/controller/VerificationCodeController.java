package com.hanfz.controller;

import com.hanfz.VerificationCodeDubboService;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.service.VerificationCodeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-05-07 15:59
 */

@DubboService
@RestController
@RequestMapping("/cms/verification-code")
public class VerificationCodeController implements VerificationCodeDubboService {

    @Autowired
    private VerificationCodeService verificationCodeService;


    @Override
    @GetMapping(value = "/send", name = "发送手机验证码")
    public ResponseData sendPhoneVerificationCode(String phone){
        verificationCodeService.sendPhoneVerificationCode(phone);
        return ResponseData.success("success");
    }

    @Override
    @GetMapping (value = "/auth", name = "校验验证码是否正确")
    public ResponseData authVerificationCode(String phone, String verificationCode){
        //校验验证码是否合法
        if(verificationCode == null || verificationCode.length() != 6){
            throw new GlobalException("请输入6位验证码");
        }

        verificationCodeService.authVerificationCode(phone, verificationCode);
        return ResponseData.success("验证码正确");
    }

}
