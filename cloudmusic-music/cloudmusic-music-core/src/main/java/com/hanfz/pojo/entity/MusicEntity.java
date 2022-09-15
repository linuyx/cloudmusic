package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Linuyx
 * @Description music
 * @Date Created in 2021-12-22 11:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MusicEntity extends BaseEntity {

    /**
     * 音乐名称
     */
    private String name;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 歌手id
     */
    private Long singerId;

    /**
     * 音乐类型id
     */
    private Long typeId;

}

