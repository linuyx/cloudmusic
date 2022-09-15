package com.hanfz.pojo.param.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-24 20:04
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectParam implements Serializable {

    /**
     * 资源路径
     */
    @Size(max = 255, message = "资源路径(url)长度不能超过255个字符")
    private String url;

    /**
     * 请求方式
     */
    @Size(max = 16, message = "请求方式(method)长度不能超过16个字符")
    private String method;

    /**
     * 资源名称
     */
    @Size(max = 64, message = "资源名称(name)长度不能超过64个字符")
    private String name;

    /**
     * 所属角色名称
     */
    private Long roleId;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页必须大于等于1")
    private Long current;

    /**
     * 每页大小
     */
    @NotNull(message = "每页大小不能为空")
    @Min(value = 1, message = "每页大小必须大于等于1")
    private Long size;

}
