package com.hanfz.controller;

import com.hanfz.OssDubboService;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.OssVO;
import com.hanfz.service.OssService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-31 16:23
 */

@DubboService
@RestController
@RequestMapping("/cms/oss")
public class OssController implements OssDubboService {

    @Autowired
    private OssService ossService;


    @Override
    @GetMapping(name = "查询OSS配置信息")
    public ResponseData<OssVO> getOssConfig(){
        return ResponseData.success("查询成功", ossService.getOssConfig());
    }

}
