package com.hanfz;

import com.hanfz.pojo.param.musictype.EditParam;
import com.hanfz.pojo.param.musictype.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.musictype.MusicTypeVO;

import java.util.List;

/**
 * @Author Hanfz
 * @Description
 * @Date Created in 2022-08-25 20:58
 */
public interface MusicTypeDubboService {

    /**
     * 查询所有音乐类型
     *
     * @return
     */
    ResponseData<List<MusicTypeVO>> getAllMusicTypes();

    /**
     * 多条件分页查询音乐类型
     *
     * @param selectParam
     * @return
     */
    ResponseData<PageData<MusicTypeVO>> getMusicTypes(SelectParam selectParam);

    /**
     * 新增音乐类型
     *
     * @param editParam
     * @return
     */
    ResponseData insertMusicType(EditParam editParam);

    /**
     * 更新音乐类型
     *
     * @param editParam
     * @return
     */
    ResponseData updateMusicType(EditParam editParam);

    /**
     * 删除音乐类型
     *
     * @param ids 音乐类型id集合
     * @return
     */
    ResponseData deleteMusicTypes(List<Long> ids);

}
