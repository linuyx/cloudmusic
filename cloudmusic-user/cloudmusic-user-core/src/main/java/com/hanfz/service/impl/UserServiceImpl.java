package com.hanfz.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.hanfz.SnowFlakeDubboService;
import com.hanfz.VerificationCodeDubboService;
import com.hanfz.dao.mapper.UserMapper;
import com.hanfz.enums.ResponseEnum;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.entity.UserEntity;
import com.hanfz.pojo.param.LoginParam;
import com.hanfz.pojo.param.RegisterParam;
import com.hanfz.pojo.request.RequestDataApp;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.service.UserService;
import com.hanfz.utils.JwtUtils;
import com.hanfz.utils.LinuyxUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 19:54
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @DubboReference
    private SnowFlakeDubboService snowFlakeService;

    @DubboReference
    private VerificationCodeDubboService verificationCodeService;

    /**
     * 随机盐位数
     */
    private final Integer SALT_NUMBER = 6;


    /**
     * 发送注册验证码
     *
     * @param phone 手机号
     * @return
     */
    @Override
    public void sendRegisterVerificationCode(String phone) {
        //判断手机号是否被注册
        if(userMapper.getUserByPhone(phone) != null){
            throw new GlobalException("该手机号已被注册");
        }

        //发送验证码
        ResponseData response = verificationCodeService.sendPhoneVerificationCode(phone);
        if (!ResponseEnum.SUCCESS.getCode().equals(response.getCode())){
            throw new GlobalException(response.getCode(), response.getMessage());
        }
    }

    /**
     * 用户注册
     *
     * @return userId
     */
    @Override
    public void register(RegisterParam registerParam) {
        //判断验证码是否正确
        ResponseData response = verificationCodeService.authVerificationCode(
                registerParam.getPhone(), registerParam.getVerificationCode());

        if(!ResponseEnum.SUCCESS.getCode().equals(response.getCode())){
            throw new GlobalException(response.getCode(), response.getMessage());
        }

        //获取随机盐
        String salt = LinuyxUtils.getSalt(SALT_NUMBER);
        //MD5加密(明文 + 随机盐)
        String password = DigestUtil.md5Hex(registerParam.getPassword() + salt);
        //密文替换明文密码、随机盐赋值
        registerParam.setPassword(password);
        registerParam.setSalt(salt);

        //获取分布式id
        Long userId = snowFlakeService.getSnowFlake().getData();
        registerParam.setUserId(userId);

        try {
            //新增用户
            userMapper.insertUser(registerParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该手机号已被注册");
        }
    }

    /**
     * 用户登录
     *
     * @param loginParam 登录请求体
     * @return
     */
    @Override
    public String login(LoginParam loginParam) {
        //根据手机号查询用户
        UserEntity userEntity = userMapper.getUserByPhone(loginParam.getPhone());
        if(userEntity == null){
            throw new GlobalException("手机号不存在");
        }

        //判断密码是否正确
        String md5Password = DigestUtil.md5Hex(loginParam.getPassword() + userEntity.getSalt());
        if(!md5Password.equals(userEntity.getPassword())){
            throw new GlobalException("密码错误");
        }

        //认证通过,生成Jwt
        return JwtUtils.createJwt(new RequestDataApp(userEntity.getId(), userEntity.getPhone()));
    }

}
