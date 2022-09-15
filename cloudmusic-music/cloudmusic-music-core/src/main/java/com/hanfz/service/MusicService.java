package com.hanfz.service;

import com.hanfz.pojo.param.music.EditParam;
import com.hanfz.pojo.param.music.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.music.MusicVO;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-07 18:34
 */
public interface MusicService {

    /**
     * 多条件分页查询音乐
     *
     * @param selectParam
     * @return
     */
    PageData<MusicVO> getMusics(SelectParam selectParam);

    /**
     * 新增音乐
     *
     * @param editParam
     */
    void insertMusic(EditParam editParam);

    /**
     * 更新音乐
     *
     * @param editParam
     */
    void updateMusic(EditParam editParam);

    /**
     * 删除音乐
     *
     * @param ids
     */
    void deleteMusics(List<Long> ids);

}
