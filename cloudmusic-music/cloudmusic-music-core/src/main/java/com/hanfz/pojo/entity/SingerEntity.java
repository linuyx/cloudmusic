package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Linuyx
 * @Description singer表
 * @Date Created in 2021-12-22 11:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SingerEntity extends BaseEntity {

    /**
     * 歌手姓名
     */
    private String name;

    /**
     * 性别: 1 男， 0 女
     */
    private Integer sex;

    /**
     * 歌手简介
     */
    private String introduction;

}

