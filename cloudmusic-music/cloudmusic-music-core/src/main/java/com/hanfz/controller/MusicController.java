package com.hanfz.controller;

import cn.hutool.core.collection.CollUtil;
import com.hanfz.MusicDubboService;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.music.EditParam;
import com.hanfz.pojo.param.music.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.music.MusicVO;
import com.hanfz.service.MusicService;
import com.hanfz.valid.Insert;
import com.hanfz.valid.Update;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-12-22 11:16
 */

@DubboService
@RestController
@RequestMapping("/musics")
public class MusicController implements MusicDubboService {

    @Autowired
    private MusicService musicService;


    @Override
    @GetMapping(name = "多条件分页查询音乐")
    public ResponseData<PageData<MusicVO>> getMusics(@Valid SelectParam selectParam){
        return ResponseData.success("success", musicService.getMusics(selectParam));
    }

    @Override
    @PostMapping(name = "新增音乐")
    public ResponseData insertMusic(@Validated(Insert.class) @RequestBody EditParam editParam){
        musicService.insertMusic(editParam);
        return ResponseData.success("上传成功");
    }

    @Override
    @PutMapping(name = "更新音乐")
    public ResponseData updateMusic(@Validated(Update.class) @RequestBody EditParam editParam){
        musicService.updateMusic(editParam);
        return ResponseData.success("更新成功");
    }

    @Override
    @DeleteMapping(name = "删除音乐")
    public ResponseData deleteMusics(@RequestBody List<Long> ids){
        if(CollUtil.isEmpty(ids)){
            throw new GlobalException("id不能为空");
        }

        musicService.deleteMusics(ids);
        return ResponseData.success("删除成功");
    }

}
