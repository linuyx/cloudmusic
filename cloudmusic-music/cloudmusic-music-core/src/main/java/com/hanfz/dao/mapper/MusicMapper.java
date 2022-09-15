package com.hanfz.dao.mapper;

import com.hanfz.pojo.param.music.EditParam;
import com.hanfz.pojo.param.music.SelectParam;
import com.hanfz.pojo.vo.music.MusicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-07 18:35
 */

@Mapper
public interface MusicMapper {

    /**
     * 统计多条件分页查询音乐总数
     *
     * @param selectParam
     * @return
     */
    Long countMusic(SelectParam selectParam);

    /**
     * 多条件分页查询音乐
     *
     * @param selectParam
     * @return
     */
    List<MusicVO> getMusics(SelectParam selectParam);

    /**
     * 根据音乐名称和歌手id判断音乐是否存在-用于编辑时
     *
     * @param editParam
     * @return
     */
    Integer existMusicEdit(EditParam editParam);

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
    void deleteMusics(@Param("ids") List<Long> ids);

    /**
     * 判断是否有音乐引用了要删除的音乐类型
     *
     * @param musicTypeIds
     * @return
     */
    Integer existMusicByMusicTypeIds(@Param("musicTypeIds") List<Long> musicTypeIds);

}
