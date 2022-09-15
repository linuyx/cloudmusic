package com.hanfz.pojo.vo.music;

import com.hanfz.pojo.vo.BaseVO;
import lombok.*;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-08 17:43
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MusicVO extends BaseVO {

    /**
     * 音乐名称
     */
    private String name;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 封面访问地址
     */
    private String imageUrl;

    /**
     * 音乐访问地址
     */
    private String musicUrl;

    /**
     * 歌词访问地址
     */
    private String lrcUrl;

    /**
     * 音乐类型
     */
    private String type;

}
