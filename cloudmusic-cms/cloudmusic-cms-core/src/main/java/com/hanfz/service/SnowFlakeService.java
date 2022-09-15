package com.hanfz.service;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 21:52
 */
public interface SnowFlakeService {

    /**
     * 根据雪花算法生成分布式id
     *
     * @return
     */
    Long getSnowFlake();

    /**
     * 根据雪花算法生成分布式id
     *
     * @param number id个数
     * @return
     */
    List<Long> getSnowFlakes(Integer number);

}
