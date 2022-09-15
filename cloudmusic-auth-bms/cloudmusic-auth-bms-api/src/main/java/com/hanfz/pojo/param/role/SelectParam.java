package com.hanfz.pojo.param.role;

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
 * @Date Created in 2022-01-26 19:03
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectParam implements Serializable {

    /**
     * 角色名称
     */
    @Size(max = 32, message = "角色名称(name)长度不能超过32个字符")
    private String name;

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
