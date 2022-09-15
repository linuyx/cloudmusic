package com.hanfz.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-31 16:34
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OssVO implements Serializable {

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
