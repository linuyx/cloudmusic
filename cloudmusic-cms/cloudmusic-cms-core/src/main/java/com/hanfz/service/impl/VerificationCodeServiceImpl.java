package com.hanfz.service.impl;

import com.hanfz.enums.ResponseEnum;
import com.hanfz.exception.GlobalException;
import com.hanfz.service.VerificationCodeService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-05-07 16:03
 */

@Slf4j
@Service
@RefreshScope
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Value("${custom.shortMessage.secretId}")
    private String secretId;

    @Value("${custom.shortMessage.secretKey}")
    private String secretKey;

    @Value("${custom.shortMessage.sdkAppId}")
    private String sdkAppId;

    @Value("${custom.shortMessage.signName}")
    private String signName;

    @Value("${custom.shortMessage.templateId}")
    private String templateId;

    /**
     * 验证码位数
     */
    private final Integer VERIFICATION_CODE_NUMBER = 6;

    /**
     * 验证码过期时间
     */
    private final Long VERIFICATION_CODE_EXPIRED_TIME = 600L;

    /**
     * 验证码时间类型
     */
    private final TimeUnit VERIFICATION_CODE_EXPIRED_TYPE = TimeUnit.SECONDS;

    /**
     * 验证码规定时间内不能重复发送(一分钟)
     */
    private final Long VERIFICATION_CODE_REPEAT_TIME = 60L;

    /**
     * 验证码redis key:cms:verification_code:手机号
     */
    public final String VERIFICATION_CODE_KEY = "cms:verification_code:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 发送手机验证码
     *
     * @param phone 手机号
     * @return
     */
    @Override
    public void sendPhoneVerificationCode(String phone) {
        //规定时间内不能重复发送
        Long expireTime = redisTemplate.getExpire(VERIFICATION_CODE_KEY + phone, TimeUnit.SECONDS);
        if(VERIFICATION_CODE_EXPIRED_TIME - expireTime < VERIFICATION_CODE_REPEAT_TIME ){
            throw new GlobalException(
                    VERIFICATION_CODE_REPEAT_TIME - (VERIFICATION_CODE_EXPIRED_TIME - expireTime)
                    + "s后可重试"
            );
        }

        Credential credential = new Credential(secretId, secretKey);
        SmsClient client = new SmsClient(credential, "ap-guangzhou");
        SendSmsRequest request = new SendSmsRequest();
        //基本信息
        request.setSmsSdkAppId(sdkAppId);
        request.setSignName(signName);
        request.setTemplateId(templateId);
        //生成验证码
        String verificationCode = getVerificationCode();
        request.setTemplateParamSet(new String[]{verificationCode});
        //接收的手机号
        request.setPhoneNumberSet(new String[]{"+86" + phone});

        try {
            //发送
            client.SendSms(request);
            //验证码存入redis
            redisTemplate.opsForValue().set(
                    VERIFICATION_CODE_KEY + phone,
                    verificationCode,
                    VERIFICATION_CODE_EXPIRED_TIME,
                    VERIFICATION_CODE_EXPIRED_TYPE
            );
        }catch (Exception ex){
            log.warn(ex.toString());
            throw new GlobalException("短信发送失败");
        }
    }

    /**
     * 校验验证码是否正确
     *
     * @param phone            手机号
     * @param verificationCode 验证码
     */
    @Override
    public void authVerificationCode(String phone, String verificationCode) {
        //根据手机号获取redis中存储的验证码
        String verificationCodeRedis = (String)redisTemplate.opsForValue().get(VERIFICATION_CODE_KEY + phone);

        //校验验证码是否正确
        if (!verificationCode.equals(verificationCodeRedis)){
            throw new GlobalException(
                    ResponseEnum.VERIFICATION_CODE_ERROR.getCode(),
                    ResponseEnum.VERIFICATION_CODE_ERROR.getMessage()
            );
        }

        //如果验证码正确,那么就删除此验证码,以防再次使用
        redisTemplate.delete(VERIFICATION_CODE_KEY + phone);
    }

    /**
     * 生成验证码
     *
     * @return 验证码
     */
    private String getVerificationCode(){
        StringBuilder builder = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < VERIFICATION_CODE_NUMBER; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }

}
