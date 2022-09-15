package com.hanfz.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-18 20:52
 */

@RefreshScope
@Configuration
public class RedissonConfig {

    @Value("${custom.redisson.address}")
    private String address;

    @Value("${custom.redisson.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(address).setPassword(password);
        return Redisson.create(config);
    }

}
