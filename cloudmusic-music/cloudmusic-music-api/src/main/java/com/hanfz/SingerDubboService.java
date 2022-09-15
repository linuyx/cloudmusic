package com.hanfz;

import com.hanfz.pojo.param.singer.EditParam;
import com.hanfz.pojo.param.singer.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.singer.SingerVO;
import java.util.List;

/**
 * @Author Hanfz
 * @Description
 * @Date Created in 2022-08-25 21:01
 */
public interface SingerDubboService {

    /**
     * 查询所有歌手
     *
     * @return
     */
    ResponseData<List<SingerVO>> getAllSingers();

    /**
     * 多条件分页查询歌手
     *
     * @param selectParam
     * @return
     */
    ResponseData<PageData<SingerVO>> getSingers(SelectParam selectParam);

    /**
     * 新增歌手
     *
     * @param editParam
     * @return
     */
    ResponseData insertSinger(EditParam editParam);

    /**
     * 更新歌手
     *
     * @param editParam
     * @return
     */
    ResponseData updateSinger(EditParam editParam);

    /**
     * 删除歌手
     *
     * @param ids
     * @return
     */
    ResponseData deleteSingers(List<Long> ids);

}
