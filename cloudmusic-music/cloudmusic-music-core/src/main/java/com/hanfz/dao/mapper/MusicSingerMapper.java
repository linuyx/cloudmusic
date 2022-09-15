package com.hanfz.dao.mapper;

import com.hanfz.pojo.entity.MusicSingerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-03-01 13:55
 */

@Mapper
public interface MusicSingerMapper {

    /**
     * 新增音乐、歌手对应关系
     *
     * @param musicSingerEntitys
     */
    void insertMusicSinger(@Param("musicSingerEntitys") List<MusicSingerEntity> musicSingerEntitys);

    /**
     * 根据音乐id删除音乐、歌手对应关系
     *
     * @param musicIds
     */
    void deleteMusicSingerByMusicIds(@Param("musicIds") List<Long> musicIds);

    /**
     * 根据歌手id集合判断是否有音乐引用要删除的歌手
     *
     * @param singerIds
     * @return
     */
    Integer existMusicSingerBySingerIds(@Param("singerIds") List<Long> singerIds);

}
