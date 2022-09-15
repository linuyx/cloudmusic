package com.hanfz.pojo.vo.resource;

import com.hanfz.pojo.vo.BaseVO;
import lombok.*;

import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-03-03 20:53
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceBaseVO extends BaseVO implements Serializable {

    /**
     * 资源名称
     */
    private String name;

}
