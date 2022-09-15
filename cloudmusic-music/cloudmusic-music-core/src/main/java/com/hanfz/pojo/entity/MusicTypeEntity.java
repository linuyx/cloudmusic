package com.hanfz.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Linuyx
 * @Description music_type表
 * @Date Created in 2021-12-22 11:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MusicTypeEntity extends BaseEntity {

    /**
     * 类型名称
     */
    private String name;

}

