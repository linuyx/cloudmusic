package com.hanfz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.hanfz.SnowFlakeDubboService;
import com.hanfz.dao.mapper.MusicMapper;
import com.hanfz.dao.mapper.MusicSingerMapper;
import com.hanfz.dao.mapper.MusicTypeMapper;
import com.hanfz.dao.mapper.SingerMapper;
import com.hanfz.enums.EditEnum;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.entity.MusicSingerEntity;
import com.hanfz.pojo.param.music.EditParam;
import com.hanfz.pojo.param.music.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.music.MusicVO;
import com.hanfz.service.MusicService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-07 18:35
 */

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private MusicTypeMapper musicTypeMapper;

    @Autowired
    private SingerMapper singerMapper;

    @DubboReference
    private SnowFlakeDubboService snowFlakeService;

    @Autowired
    private MusicSingerMapper musicSingerMapper;


    /**
     * 多条件分页查询音乐
     *
     * @param selectParam
     * @return
     */
    @Override
    public PageData<MusicVO> getMusics(SelectParam selectParam) {
        //统计条数
        Long totalCount = musicMapper.countMusic(selectParam);

        if(totalCount == 0){
            return PageData.<MusicVO>builder()
                    .totalCount(0L)
                    .build();
        }

        //多条分页查询数据
        selectParam.setCurrent((selectParam.getCurrent() - 1) * selectParam.getSize());
        List<MusicVO> musicList = musicMapper.getMusics(selectParam);

        //构建分页返回对象
        return PageData.<MusicVO>builder()
                .totalCount(totalCount)
                .data(musicList)
                .build();
    }

    /**
     * 新增音乐
     *
     * @param editParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMusic(EditParam editParam) {
        editMusic(editParam, EditEnum.INSERT);
    }

    /**
     * 更新音乐
     *
     * @param editParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMusic(EditParam editParam) {
        editMusic(editParam, EditEnum.UPDATE);
    }

    /**
     * 删除音乐
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMusics(List<Long> ids) {
        //删除音乐
        musicMapper.deleteMusics(ids);
        //根据音乐id集合删除音乐、歌手对应关系
        musicSingerMapper.deleteMusicSingerByMusicIds(ids);
    }

    /**
     * 编辑音乐
     *
     * @param editParam 编辑请求体
     * @param editEnum 编辑类型
     */
    @Transactional(rollbackFor = Exception.class)
    public void editMusic(EditParam editParam, EditEnum editEnum){
        //根据音乐名称和歌手id判断是否存在
        if(musicMapper.existMusicEdit(editParam) == editParam.getSingerIds().size()){
            throw new GlobalException("该音乐已存在");
        }

        //判断音乐类型id是否存在
        Integer existMusicType = musicTypeMapper.existTypeById(editParam.getTypeId());
        if(ObjectUtil.isNull(existMusicType)){
            throw new GlobalException("音乐类型不存在");
        }

        //根据id集合判断歌手是否存在
        List<String> singerNames = singerMapper.getSingerNameByIds(editParam.getSingerIds());
        if(singerNames.size() != editParam.getSingerIds().size()){
            throw new GlobalException("歌手不存在");
        }

        //拼接歌手名
        if(singerNames.size() == 1){
            editParam.setSinger(singerNames.get(0));
        }else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0, n = singerNames.size(); i < n; i++) {
                stringBuilder.append(singerNames.get(i));
                if(i == n - 1){
                    break;
                }
                stringBuilder.append("/");
            }
            editParam.setSinger(stringBuilder.toString());
        }

        //开始新增或更新的逻辑
        if(editEnum == EditEnum.INSERT){
            //获取分布式id
            int n = editParam.getSingerIds().size();
            List<Long> ids = snowFlakeService.getSnowFlakes(n + 1).getData();
            editParam.setId(ids.get(0));

            try {
                //新增音乐
                musicMapper.insertMusic(editParam);
            }catch (DuplicateKeyException ex) {
                throw new GlobalException("该音乐已存在");
            }

            //构建音乐、歌手对应关系对象
            List<MusicSingerEntity> entitys = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                MusicSingerEntity musicSingerEntity = MusicSingerEntity.builder()
                        .id(ids.get(i + 1))
                        .musicId(ids.get(0))
                        .singerId(editParam.getSingerIds().get(i))
                        .build();

                entitys.add(musicSingerEntity);
            }

            //新增音乐、歌手对应关系
            musicSingerMapper.insertMusicSinger(entitys);
        }else if(editEnum == EditEnum.UPDATE){
            try {
                //更新音乐
                musicMapper.updateMusic(editParam);
            }catch (DuplicateKeyException ex) {
                throw new GlobalException("该音乐已存在");
            }

            //更新音乐、歌手对应关系(先删除再新增)
            musicSingerMapper.deleteMusicSingerByMusicIds(List.of(editParam.getId()));

            //获取分布式id
            int n = editParam.getSingerIds().size();
            List<Long> ids = snowFlakeService.getSnowFlakes(n).getData();
            //构建音乐、歌手对应关系对象
            List<MusicSingerEntity> musicSingerEntitys = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                MusicSingerEntity musicSingerEntity = MusicSingerEntity.builder()
                        .id(ids.get(i))
                        .musicId(editParam.getId())
                        .singerId(editParam.getSingerIds().get(i))
                        .build();
                musicSingerEntitys.add(musicSingerEntity);
            }

            //新增音乐、歌手对应关系
            musicSingerMapper.insertMusicSinger(musicSingerEntitys);
        }
    }

}
