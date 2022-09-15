package com.hanfz.service;

import com.hanfz.pojo.param.singer.EditParam;
import com.hanfz.pojo.param.singer.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.singer.SingerVO;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-22 18:18
 */
public interface SingerService {

    /**
     * 查询所有歌手
     *
     * @return
     */
    List<SingerVO> getAllSingers();

    /**
     * 多条件分页查询歌手
     *
     * @param selectParam
     * @return
     */
    PageData<SingerVO> getSingers(SelectParam selectParam);

    /**
     * 新增歌手
     *
     * @param editParam
     */
    void insertSinger(EditParam editParam);

    /**
     * 更新歌手
     *
     * @param editParam
     */
    void updateSinger(EditParam editParam);

    /**
     * 删除歌手
     *
     * @param ids
     */
    void deleteSingers(List<Long> ids);

}
