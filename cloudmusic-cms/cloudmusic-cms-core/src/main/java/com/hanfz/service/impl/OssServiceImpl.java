package com.hanfz.service.impl;

import com.hanfz.config.OssConfig;
import com.hanfz.pojo.vo.OssVO;
import com.hanfz.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-31 16:26
 */

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssConfig ossConfig;


    /**
     * 查询OSS配置信息
     *
     * @return
     */
    @Override
    public OssVO getOssConfig() {
        return new OssVO(
                ossConfig.getRegion(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret(),
                ossConfig.getBucket()
        );
    }

}
