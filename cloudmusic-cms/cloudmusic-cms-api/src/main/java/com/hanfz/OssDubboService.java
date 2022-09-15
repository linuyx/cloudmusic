package com.hanfz;

import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.OssVO;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-08-17 23:38
 */
public interface OssDubboService {

    /**
     * 查询OSS配置信息
     *
     * @return
     */
    ResponseData<OssVO> getOssConfig();

}
