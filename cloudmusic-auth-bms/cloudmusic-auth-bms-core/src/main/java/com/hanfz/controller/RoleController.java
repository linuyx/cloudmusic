package com.hanfz.controller;

import cn.hutool.core.collection.CollUtil;
import com.hanfz.RoleDubboService;
import com.hanfz.exception.GlobalException;
import com.hanfz.pojo.param.role.EditParam;
import com.hanfz.pojo.param.role.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.role.RoleVO;
import com.hanfz.service.RoleService;
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
 * @Date Created in 2022-01-26 18:59
 */

@DubboService
@RestController
@RequestMapping("/auth-bms/roles")
public class RoleController implements RoleDubboService {

    @Autowired
    private RoleService roleService;


    @Override
    @GetMapping(value = "/all", name = "查询所有角色")
    public ResponseData<List<RoleVO>> getAllRoles(){
        return ResponseData.success("success", roleService.getAllRoles());
    }

    @Override
    @GetMapping(name = "多条件查询角色")
    public ResponseData<PageData<RoleVO>> getRoles(@Valid SelectParam selectParam){
        return ResponseData.success("success", roleService.getRoles(selectParam));
    }

    @Override
    @PostMapping(name = "新增角色")
    public ResponseData insertRole(@Validated(Insert.class) @RequestBody EditParam editParam){
        roleService.insertRole(editParam);
        return ResponseData.success("新增成功");
    }

    @Override
    @PutMapping(name = "更新角色")
    public ResponseData updateRole(@Validated(Update.class) @RequestBody EditParam editParam){
        roleService.updateRole(editParam);
        return ResponseData.success("更新成功");
    }

    @Override
    @DeleteMapping(name = "删除角色")
    public ResponseData deleteRoles(@RequestBody List<Long> ids){
        if(CollUtil.isEmpty(ids)){
            throw new GlobalException("id不能为空");
        }

        roleService.deleteRoles(ids);
        return ResponseData.success("删除成功");
    }

}
