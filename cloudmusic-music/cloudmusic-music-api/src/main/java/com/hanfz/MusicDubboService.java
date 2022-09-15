package com.hanfz;

import com.hanfz.pojo.param.music.EditParam;
import com.hanfz.pojo.param.music.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.music.MusicVO;
import java.util.List;

/**
 * @Author Hanfz
 * @Description
 * @Date Created in 2022-08-25 20:56
 */
public interface MusicDubboService {

    /**
     * 多条件分页查询音乐
     *
     * @param selectParam
     * @return
     */
    ResponseData<PageData<MusicVO>> getMusics(SelectParam selectParam);

    /**
     * 新增音乐
     *
     * @param editParam
     * @return
     */
    ResponseData insertMusic(EditParam editParam);

    /**
     * 更新音乐
     *
     * @param editParam
     * @return
     */
    ResponseData updateMusic(EditParam editParam);

    /**
     * 删除音乐
     *
     * @param ids 音乐id集合
     * @return
     */
    ResponseData deleteMusics(List<Long> ids);

}
