package com.hanfz;

import com.hanfz.pojo.response.ResponseData;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-08-18 0:19
 */
public interface SnowFlakeDubboService {

    /**
     * 根据雪花算法生成分布式id
     *
     * @return
     */
    ResponseData<Long> getSnowFlake();

    /**
     * 根据雪花算法生成多个分布式id
     *
     * @param number
     * @return
     */
    ResponseData<List<Long>> getSnowFlakes(Integer number);

}
