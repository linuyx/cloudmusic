package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-03-01 14:30
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MusicSingerEntity extends BaseEntity {

    /**
     * 音乐id
     */
    private Long musicId;

    /**
     * 歌手id
     */
    private Long singerId;

}
