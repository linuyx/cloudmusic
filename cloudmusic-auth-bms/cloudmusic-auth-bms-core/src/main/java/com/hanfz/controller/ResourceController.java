package com.hanfz.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.hanfz.ResourceDubboService;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.resource.EditParam;
import com.hanfz.pojo.param.resource.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.resource.ResourceBaseVO;
import com.hanfz.pojo.vo.resource.ResourceVO;
import com.hanfz.service.ResourceService;
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
 * @Date Created in 2022-01-24 19:49
 */

@DubboService
@RestController
@RequestMapping("/auth-bms/resources")
public class ResourceController implements ResourceDubboService {

    @Autowired
    private ResourceService resourceService;


    @Override
    @GetMapping(name = "多条件查询资源")
    public ResponseData<PageData<ResourceVO>> getResources(@Valid SelectParam selectParam){
        String method = selectParam.getMethod();
        if(StrUtil.isNotBlank(method)){
            if(!method.matches("(GET|POST|PUT|DELETE)")){
                throw new GlobalException("请求方法(method)只能为:GET|POST|PUT|DELETE");
            }
        }

        return ResponseData.success("success", resourceService.getResources(selectParam));
    }

    @Override
    @GetMapping(value = "/all", name = "查询所有资源")
    public ResponseData<List<ResourceBaseVO>> getAllResources(){
        return ResponseData.success("success", resourceService.getAllResources());
    }

    @Override
    @GetMapping(value = "/by-role-id" ,name = "根据角色id查询资源id")
    public ResponseData<List<ResourceBaseVO>> getResourcesByRoleId(Long roleId){
        return ResponseData.success("success", resourceService.getResourcesByRoleId(roleId));
    }

    @Override
    @PostMapping(name = "新增资源")
    public ResponseData insertResource(@Validated(Insert.class) @RequestBody EditParam editParam){
        resourceService.insertResource(editParam);
        return ResponseData.success("新增成功");
    }

    @Override
    @PutMapping(name = "更新资源")
    public ResponseData updateResource(@Validated(Update.class) @RequestBody EditParam editParam){
        resourceService.updateResource(editParam);
        return ResponseData.success("更新成功");
    }

    @Override
    @DeleteMapping(name = "删除资源")
    public ResponseData deleteResources(@RequestBody List<Long> ids){
        if(CollUtil.isEmpty(ids)){
            throw new GlobalException("id不能为空");
        }

        resourceService.deleteResources(ids);
        return ResponseData.success("删除成功");
    }

}
