package com.hanfz.pojo.vo.role;

import com.hanfz.pojo.vo.BaseVO;
import lombok.*;

import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-26 19:00
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleVO extends BaseVO implements Serializable {

    /**
     * 名称
     */
    private String name;

}
