package com.hanfz.pojo.vo.singer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hanfz.pojo.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-30 13:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SingerVO extends BaseVO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别:1(男)/0(女)
     */
    private Integer sex;

    /**
     * 简介
     */
    private String introduction;

}
