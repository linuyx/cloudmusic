package com.hanfz.pojo.vo.musictype;

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
public class MusicTypeVO extends BaseVO {

    /**
     * 类型名称
     */
    private String name;

}
