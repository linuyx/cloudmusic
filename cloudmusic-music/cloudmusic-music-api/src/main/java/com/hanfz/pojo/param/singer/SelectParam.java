package com.hanfz.pojo.param.singer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Linuyx
 * @Description 多条件分页查询歌手请求体
 * @Date Created in 2021-12-22 18:20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SelectParam {

    /**
     * 歌手姓名
     */
    @Size(max = 32, message = "歌手不能超过32个字符")
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
