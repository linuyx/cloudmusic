package com.hanfz.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-08-22 16:47
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO implements Serializable {

    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
