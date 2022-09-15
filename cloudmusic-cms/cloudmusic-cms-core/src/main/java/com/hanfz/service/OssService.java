package com.hanfz.service;


import com.hanfz.pojo.vo.OssVO;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-31 16:25
 */
public interface OssService {

    /**
     * 查询OSS配置信息
     *
     * @return
     */
    OssVO getOssConfig();

}
