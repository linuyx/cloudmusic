package com.hanfz.dao.mapper;

import com.hanfz.pojo.param.singer.EditParam;
import com.hanfz.pojo.param.singer.SelectParam;
import com.hanfz.pojo.vo.singer.SingerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-22 18:29
 */

@Mapper
public interface SingerMapper{

    /**
     * 多条件统计歌手
     *
     * @param selectParam
     * @return
     */
    Long countSinger(SelectParam selectParam);

    /**
     * 多条件查询歌手
     *
     * @param selectParam
     * @return
     */
    List<SingerVO> getSingers(SelectParam selectParam);

    /**
     * 根据歌手姓名和性别判断歌手是否存在-用于编辑时
     *
     * @param editParam
     * @return
     */
    Integer existSingerEdit(EditParam editParam);

    /**
     * 根据歌手id集合查询歌手名
     *
     * @param singerIds
     * @return
     */
    List<String> getSingerNameByIds(@Param("singerIds") List<Long> singerIds);

    /**
     * 查询所有歌手
     *
     * @return
     */
    List<SingerVO> getAllSingers();

    /**
     * 新增歌手
     *
     * @param editParam
     */
    void insertSinger(EditParam editParam);

    /**
     * 更新歌手信息
     *
     * @param editParam
     */
    void updateSinger(EditParam editParam);

    /**
     * 删除歌手
     *
     * @param ids
     */
    void deleteSingers(@Param("ids") List<Long> ids);

}
