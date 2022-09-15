package com.hanfz.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.hanfz.service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 21:53
 */

@RefreshScope
@Service
public class SnowFlakeServiceImpl implements SnowFlakeService {

    /**
     * 机器id
     */
    @Value("${custom.snowflake.workerId}")
    private Long workerId;

    /**
     * 数据中心id
     */
    @Value("${custom.snowflake.datacenterId}")
    private Long datacenterId;

    /**
     * 雪花算法实例
     */
    private Snowflake snowflake;

    @PostConstruct
    public void initSnowFlake(){
        snowflake= IdUtil.getSnowflake(workerId, datacenterId);
    }

    /**
     * 根据雪花算法生成分布式id
     *
     * @return
     */
    @Override
    public Long getSnowFlake() {
        return snowflake.nextId();
    }

    /**
     * 根据雪花算法生成多个分布式id
     *
     * @return
     */
    @Override
    public List<Long> getSnowFlakes(Integer number) {
        List<Long> snowFlakes = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            snowFlakes.add(snowflake.nextId());
        }
        return snowFlakes;
    }

}
