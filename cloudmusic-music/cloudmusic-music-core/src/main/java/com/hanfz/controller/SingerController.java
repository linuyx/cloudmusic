package com.hanfz.controller;

import cn.hutool.core.collection.CollUtil;
import com.hanfz.SingerDubboService;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.singer.EditParam;
import com.hanfz.pojo.param.singer.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.singer.SingerVO;
import com.hanfz.service.SingerService;
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
 * @Date Created in 2021-12-22 11:36
 */

@DubboService
@RestController
@RequestMapping("/musics/singers")
public class SingerController implements SingerDubboService {

    @Autowired
    private SingerService singerService;


    @Override
    @GetMapping(value = "/all", name = "查询所有音乐歌手")
    public ResponseData<List<SingerVO>> getAllSingers(){
        return ResponseData.success("查询成功", singerService.getAllSingers());
    }

    @Override
    @GetMapping(name = "多条件分页查询歌手")
    public ResponseData<PageData<SingerVO>> getSingers(@Valid SelectParam selectParam){
        return ResponseData.success("success", singerService.getSingers(selectParam));
    }

    @Override
    @PostMapping(name = "新增歌手")
    public ResponseData insertSinger(@Validated(Insert.class) @RequestBody EditParam editParam){
        singerService.insertSinger(editParam);
        return ResponseData.success("新增成功");
    }

    @Override
    @PutMapping(name = "更新歌手")
    public ResponseData updateSinger(@Validated(Update.class) @RequestBody EditParam editParam){
        singerService.updateSinger(editParam);
        return ResponseData.success("更新成功");
    }

    @Override
    @DeleteMapping(name = "删除歌手")
    public ResponseData deleteSingers(@RequestBody List<Long> ids){
        if(CollUtil.isEmpty(ids)){
            throw new GlobalException("id不能为空");
        }

        singerService.deleteSingers(ids);
        return ResponseData.success("删除成功");
    }

}
