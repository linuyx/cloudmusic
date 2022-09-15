package com.hanfz.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.hanfz.SnowFlakeDubboService;
import com.hanfz.dao.mapper.ResourceMapper;
import com.hanfz.dao.mapper.RoleMapper;
import com.hanfz.dao.mapper.UserMapper;
import com.hanfz.dao.mapper.UserRoleMapper;
import com.hanfz.enums.ResponseEnum;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.entity.UserEntity;
import com.hanfz.pojo.param.auth.AuthParam;
import com.hanfz.pojo.param.auth.LoginParam;
import com.hanfz.pojo.param.auth.RegisterParam;
import com.hanfz.pojo.request.RequestDataBms;
import com.hanfz.service.AuthService;
import com.hanfz.utils.JwtUtils;
import com.hanfz.utils.LinuyxUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 14:44
 */

@RefreshScope
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @DubboReference
    private SnowFlakeDubboService snowFlakeService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 随机盐位数
     */
    private static final Integer SALT_NUMBER = 6;

    /**
     * 注册用户角色
     */
    private final String registerRole = "admin";


    /**
     * 添加管理员
     *
     * @param registerParam 注册请求体
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterParam registerParam) {
        if (userMapper.existUser(registerParam.getUsername()) != null) {
            throw new GlobalException("该账号已存在");
        }

        //获取随机盐
        String salt = LinuyxUtils.getSalt(SALT_NUMBER);
        //MD5加密(明文 + 随机盐)
        String password = DigestUtil.md5Hex(registerParam.getPassword() + salt);
        //密文替换明文密码,随机盐赋值
        registerParam.setPassword(password)
                .setSalt(salt);

        //获取account分布式id,新增账号
        Long userId = snowFlakeService.getSnowFlake().getData();
        registerParam.setUserId(userId);

        try {
            //新增账号
            userMapper.insertUser(registerParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该账号已存在");
        }

        //根据角色名称查询角色id
        Long roleId = roleMapper.getIdByName(registerRole);
        //生成分布式id
        Long userRoleId = snowFlakeService.getSnowFlake().getData();
        //给用户赋予角色
        userRoleMapper.insertUserRole(userRoleId, registerParam.getUserId(), roleId);
    }

    /**
     * 登录
     *
     * @param loginParam 登录请求体
     */
    @Override
    public String login(LoginParam loginParam) {
        //根据用户名和角色查询账号信息
        UserEntity user = userMapper.getUserByUsername(loginParam.getUsername());

        if(user == null){
            throw new GlobalException("用户名不存在");
        }

        //登录密码 + salt 经过MD5加密,与数据库密码比较是否相同
        String password = DigestUtil.md5Hex(loginParam.getPassword() + user.getSalt());
        if(!password.equals(user.getPassword())){
            throw new GlobalException("密码不正确");
        }

        //登录成功
        //获取账号对应的角色
        List<String> roles = userRoleMapper.getRoleByUserId(user.getId());

        //签发JWT
        return JwtUtils.createJwt(new RequestDataBms(user.getId(), loginParam.getUsername(), roles));
    }

    /**
     * 判断用户是否有当前资源权限
     *
     * @param authParam 请求数据请求体
     * @return 当前用户信息
     */
    @Override
    public RequestDataBms auth(AuthParam authParam) {
        //解析token
        RequestDataBms requestDataBms = JwtUtils.parseJwt(authParam.getToken());
        //根据角色名称集合查询角色id
        List<Long> roleIds = roleMapper.getIdByNames(requestDataBms.getRoles());
        //判断角色集合是否有当前资源权限
        Integer result = resourceMapper.existAuthorize(roleIds, authParam.getUrl(), authParam.getMethod());

        //无权限
        if(result == null){
            throw new GlobalException(ResponseEnum.AUTH_ERROR.getCode(), ResponseEnum.AUTH_ERROR.getMessage());
        }

        return requestDataBms;
    }

}
