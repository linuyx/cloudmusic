package com.hanfz.pojo.vo.resource;

import com.hanfz.pojo.vo.BaseVO;
import lombok.*;

import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-24 19:56
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceVO extends BaseVO implements Serializable {

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

    /**
     * 资源所属角色
     */
    private String role;

}
