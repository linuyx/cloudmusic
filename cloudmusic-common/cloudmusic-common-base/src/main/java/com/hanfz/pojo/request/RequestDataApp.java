package com.hanfz.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Linuyx
 * @Description 用户信息实体
 * @Date Created in 2022-05-17 16:47
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDataApp implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 手机号
     */
    private String phone;

}
