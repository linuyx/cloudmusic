package com.hanfz.service;

import com.hanfz.pojo.param.musictype.EditParam;
import com.hanfz.pojo.param.musictype.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.musictype.MusicTypeVO;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-22 11:46
 */
public interface MusicTypeService {

    /**
     * 查询所有音乐类型
     *
     * @return
     */
    List<MusicTypeVO> getAllMusicTypes();

    /**
     * 多条件分页查询
     *
     * @param selectParam
     * @return
     */
    PageData<MusicTypeVO> getMusicTypes(SelectParam selectParam);

    /**
     * 新增音乐类型
     *
     * @param editParam
     */
    void insertMusicType(EditParam editParam);

    /**
     * 更新音乐类型
     *
     * @param editParam
     */
    void updateMusicType(EditParam editParam);

    /**
     * 删除音乐类型
     *
     * @param ids
     */
    void deleteMusicTypes(List<Long> ids);

}
