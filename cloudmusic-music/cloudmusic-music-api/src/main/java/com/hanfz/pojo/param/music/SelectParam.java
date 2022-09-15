package com.hanfz.pojo.param.music;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-08 17:35
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectParam {

    /**
     * 音乐名称
     */
    @Size(max = 32, message = "音乐名称不能超过32个字符")
    private String name;

    /**
     * 歌手id
     */
    private Long singerId;

    /**
     * 歌手id
     */
    private String singer;

    /**
     * 音乐类型id
     */
    private String type;

    /**
     * 音乐类型id
     */
    private Long typeId;

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
