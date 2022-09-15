package com.hanfz.pojo.param.music;

import com.hanfz.valid.Insert;
import com.hanfz.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-07 18:18
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditParam {

    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    /**
     * 音乐名称
     */
    @NotBlank(message = "音乐名称不能为空", groups = {Insert.class, Update.class})
    @Size(max = 32, message = "音乐名称不能超过32个字符", groups = {Insert.class, Update.class})
    private String name;

    /**
     * 封面访问地址
     */
    @NotBlank(message = "封面访问地址不能为空", groups = {Insert.class, Update.class})
    @Size(max = 255, message = "封面访问地址不能超过255个字符", groups = {Insert.class, Update.class})
    private String imageUrl;

    /**
     * 音乐访问地址
     */
    @NotBlank(message = "音乐访问地址不能为空", groups = {Insert.class, Update.class})
    @Size(max = 255, message = "音乐访问地址不能超过255个字符", groups = {Insert.class, Update.class})
    private String musicUrl;

    /**
     * 歌词访问地址
     */
    @NotBlank(message = "歌词访问地址不能为空", groups = {Insert.class, Update.class})
    @Size(max = 255, message = "歌词访问地址不能超过255个字符", groups = {Insert.class, Update.class})
    private String lrcUrl;

    /**
     * 歌手id集合
     */
    @NotEmpty(message = "歌手id不能为空", groups = {Insert.class, Update.class})
    private List<Long> singerIds;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 音乐类型id
     */
    @NotNull(message = "音乐类型id不能为空", groups = {Insert.class, Update.class})
    private Long typeId;

}
