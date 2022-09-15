package com.hanfz.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Linuyx
 * @Description 一对多映射
 * @Date Created in 2022-03-03 18:10
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtmParam {

    /**
     * id
     */
    private Long id;

    /**
     * 一对多中一的id
     */
    private Long oneId;

    /**
     * 一对多中多的id
     */
    private Long manyId;

}
