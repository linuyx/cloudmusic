package com.hanfz.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Linuyx
 * @Description 分页数据返回类
 * @Date Created in 2021-04-08 15:08
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageData<T> implements Serializable {

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 总数据量
     */
    private Long totalCount;

    /**
     * 总页数
     */
    private Long pageCount;

    /**
     * 数据
     */
    private List<T> data;

}
