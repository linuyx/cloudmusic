package com.hanfz.service.impl;

import com.hanfz.SnowFlakeDubboService;
import com.hanfz.dao.mapper.MusicMapper;
import com.hanfz.dao.mapper.MusicTypeMapper;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.musictype.EditParam;
import com.hanfz.pojo.param.musictype.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.musictype.MusicTypeVO;
import com.hanfz.service.MusicTypeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-22 11:46
 */

@Service
public class MusicTypeServiceImpl implements MusicTypeService {

    @Autowired
    private MusicTypeMapper musicTypeMapper;

    @DubboReference
    private SnowFlakeDubboService snowFlakeDubboService;

    @Autowired
    private MusicMapper musicMapper;

    /**
     * 查询所有音乐类型
     *
     * @return
     */
    @Override
    public List<MusicTypeVO> getAllMusicTypes() {
        return musicTypeMapper.getAllMusicType();
    }

    /**
     * 多条件分页查询
     *
     * @param selectParam
     * @return
     */
    @Override
    public PageData<MusicTypeVO> getMusicTypes(SelectParam selectParam) {
        //统计条数
        Long totalCount = musicTypeMapper.countMusicType(selectParam);

        if(totalCount == 0){
            return PageData.<MusicTypeVO>builder()
                    .totalCount(0L)
                    .build();
        }

        //多条分页查询数据
        selectParam.setCurrent((selectParam.getCurrent() - 1) * selectParam.getSize());
        List<MusicTypeVO> musicTypeList = musicTypeMapper.getMusicTypes(selectParam);

        return PageData.<MusicTypeVO>builder()
                .totalCount(totalCount)
                .data(musicTypeList)
                .build();
    }

    /**
     * 新增音乐类型
     *
     * @param editParam
     */
    @Override
    public void insertMusicType(EditParam editParam) {
        if(musicTypeMapper.existMusicTypeEdit(editParam) != null){
            throw new GlobalException("该音乐类型已存在");
        }

        //获取分布式id
        Long id = snowFlakeDubboService.getSnowFlake().getData();
        editParam.setId(id);

        try {
            //新增音乐类型
            musicTypeMapper.insertMusicType(editParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该音乐类型已存在");
        }
    }

    /**
     * 更新音乐类型
     *
     * @param editParam
     */
    @Override
    public void updateMusicType(EditParam editParam) {
        if(musicTypeMapper.existMusicTypeEdit(editParam) != null){
            throw new GlobalException("该音乐类型已存在");
        }

        try {
            //更新音乐类型
            musicTypeMapper.updateMusicType(editParam);
        }catch (DuplicateKeyException ex) {
            throw new GlobalException("该音乐类型已存在");
        }
    }

    /**
     * 删除音乐类型
     *
     * @param ids
     */
    @Override
    public void deleteMusicTypes(List<Long> ids) {
        //判断是否还有音乐引用要删除的类型
        if(musicMapper.existMusicByMusicTypeIds(ids) != null){
            throw new GlobalException("存在音乐引用要删除的类型，如果仍要删除，请先删除引用的音乐");
        }

        musicTypeMapper.deleteMusicTypes(ids);
    }

}
