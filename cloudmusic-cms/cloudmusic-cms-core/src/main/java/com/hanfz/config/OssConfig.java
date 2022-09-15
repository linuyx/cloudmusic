package com.hanfz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-31 10:15
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "custom.ali.oss")
public class OssConfig {

    /**
     * 区域
     */
    private String region;

    /**
     * AccessKeyId
     */
    private String accessKeyId;

    /**
     * AccessKeySecret
     */
    private String accessKeySecret;

    /**
     * bucket
     */
    private String bucket;

}
