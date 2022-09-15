package com.hanfz.service.impl;

import com.hanfz.SnowFlakeDubboService;
import com.hanfz.dao.mapper.MusicSingerMapper;
import com.hanfz.dao.mapper.SingerMapper;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.singer.EditParam;
import com.hanfz.pojo.param.singer.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.singer.SingerVO;
import com.hanfz.service.SingerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-22 18:18
 */

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private RedissonClient redissonClient;

    @DubboReference
    private SnowFlakeDubboService snowFlakeService;

    @Autowired
    private MusicSingerMapper musicSingerMapper;

    /**
     * 查询所有歌手
     *
     * @return
     */
    @Override
    public List<SingerVO> getAllSingers() {
        return singerMapper.getAllSingers();
    }

    /**
     * 多条件分页查询歌手
     *
     * @param selectParam
     * @return
     */
    @Override
    public PageData<SingerVO> getSingers(SelectParam selectParam) {
        //统计条数
        Long totalCount = singerMapper.countSinger(selectParam);

        if(totalCount == 0){
            return PageData.<SingerVO>builder()
                    .totalCount(0L)
                    .build();
        }

        //多条件分页查询数据
        selectParam.setCurrent((selectParam.getCurrent() - 1) * selectParam.getSize());
        List<SingerVO> singerList = singerMapper.getSingers(selectParam);

        return PageData.<SingerVO>builder()
                   .totalCount(totalCount)
                   .data(singerList)
                   .build();
    }

    /**
     * 新增歌手
     *
     * @param editParam
     */
    @Override
    public void insertSinger(EditParam editParam) {
        if(singerMapper.existSingerEdit(editParam) != null){
            throw new GlobalException("该歌手已存在");
        }

        //获取分布式id
        Long id = snowFlakeService.getSnowFlake().getData();
        editParam.setId(id);

        try {
            //新增歌手
            singerMapper.insertSinger(editParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该歌手已存在");
        }
    }

    /**
     * 更新歌手
     *
     * @param editParam
     */
    @Override
    public void updateSinger(EditParam editParam) {
        if(singerMapper.existSingerEdit(editParam) != null){
            throw new GlobalException("该歌手已存在");
        }

        try {
            //更新歌手
            singerMapper.updateSinger(editParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该歌手已存在");
        }
    }

    /**
     * 删除歌手
     *
     * @param ids
     */
    @Override
    public void deleteSingers(List<Long> ids) {
        //判断是否有音乐引用要删除的歌手
        if(musicSingerMapper.existMusicSingerBySingerIds(ids) != null){
            throw new GlobalException("存在音乐引用要删除的歌手，如果仍要删除，请先删除引用的音乐");
        }

        singerMapper.deleteSingers(ids);
    }

}
