package com.hanfz.controller;

import com.hanfz.SnowFlakeDubboService;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.service.SnowFlakeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-09-17 21:50
 */

@DubboService
@RestController
@RequestMapping("/cms/snowflake")
public class SnowFlakeController implements SnowFlakeDubboService {

    @Autowired
    private SnowFlakeService snowFlakeService;


    @Override
    @GetMapping(name = "根据雪花算法生成分布式id")
    public ResponseData<Long> getSnowFlake(){
        return ResponseData.success("id生成成功", snowFlakeService.getSnowFlake());
    }

    @Override
    @GetMapping(value = "/list", name = "根据雪花算法生成多个分布式id")
    public ResponseData<List<Long>> getSnowFlakes(Integer number){
        return ResponseData.success("id生成成功", snowFlakeService.getSnowFlakes(number));
    }

}
