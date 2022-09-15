package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Linuyx
 * @Description resource表
 * @Date Created in 2021-9-17 16:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ResourceEntity extends BaseEntity {

    /**
     * 资源url
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 资源名称
     */
    private String name;

}

