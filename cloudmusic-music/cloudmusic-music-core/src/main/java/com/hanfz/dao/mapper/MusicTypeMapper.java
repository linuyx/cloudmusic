package com.hanfz.dao.mapper;

import com.hanfz.pojo.param.musictype.EditParam;
import com.hanfz.pojo.param.musictype.SelectParam;
import com.hanfz.pojo.vo.musictype.MusicTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-22 11:47
 */

@Mapper
public interface MusicTypeMapper {

    /**
     * 多条件统计音乐类型
     *
     * @param selectParam
     * @return
     */
    Long countMusicType(SelectParam selectParam);

    /**
     * 多条件查询音乐类型
     *
     * @param selectParam
     * @return
     */
    List<MusicTypeVO> getMusicTypes(SelectParam selectParam);

    /**
     * 根据音乐类型名称判断音乐类型是否存在-用于编辑时
     *
     * @param editParam
     * @return
     */
    Integer existMusicTypeEdit(EditParam editParam);

    /**
     * 根据id判断音乐类型是否存在
     *
     * @param typeId
     * @return
     */
    Integer existTypeById(@Param("typeId") Long typeId);

    /**
     * 查询所有音乐类型
     *
     * @return
     */
    List<MusicTypeVO> getAllMusicType();

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
    void deleteMusicTypes(@Param("ids") List<Long> ids);

}
