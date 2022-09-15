package com.hanfz.controller;

import cn.hutool.core.collection.CollUtil;
import com.hanfz.MusicTypeDubboService;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.musictype.EditParam;
import com.hanfz.pojo.param.musictype.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.musictype.MusicTypeVO;
import com.hanfz.service.MusicTypeService;
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
@RequestMapping("/musics/types")
public class MusicTypeController implements MusicTypeDubboService {

    @Autowired
    private MusicTypeService musicTypeService;


    @Override
    @GetMapping(value = "/all", name = "查询所有音乐类型")
    public ResponseData<List<MusicTypeVO>> getAllMusicTypes(){
        return ResponseData.success("查询成功", musicTypeService.getAllMusicTypes());
    }

    @Override
    @GetMapping(name = "多条件分页查询音乐类型")
    public ResponseData<PageData<MusicTypeVO>> getMusicTypes(@Valid SelectParam selectParam){
        return ResponseData.success("查询成功", musicTypeService.getMusicTypes(selectParam));
    }

    @Override
    @PostMapping(name = "新增音乐类型")
    public ResponseData insertMusicType(@Validated(Insert.class) @RequestBody EditParam editParam){
        musicTypeService.insertMusicType(editParam);
        return ResponseData.success("新增成功");
    }

    @Override
    @PutMapping(name = "更新音乐类型")
    public ResponseData updateMusicType(@Validated(Update.class) @RequestBody EditParam editParam){
        musicTypeService.updateMusicType(editParam);
        return ResponseData.success("更新成功");
    }

    @Override
    @DeleteMapping(name = "删除音乐类型")
    public ResponseData deleteMusicTypes(@RequestBody List<Long> ids){
        if(CollUtil.isEmpty(ids)){
            throw new GlobalException("id不能为空");
        }

        musicTypeService.deleteMusicTypes(ids);
        return ResponseData.success("删除成功");
    }

}
