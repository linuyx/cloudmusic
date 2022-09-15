package com.hanfz.service;

import com.hanfz.pojo.param.resource.EditParam;
import com.hanfz.pojo.param.resource.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.vo.resource.ResourceBaseVO;
import com.hanfz.pojo.vo.resource.ResourceVO;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-01-24 20:06
 */
public interface ResourceService {

    /**
     * 多条件查询资源
     *
     * @param selectParam
     * @return
     */
    PageData<ResourceVO> getResources(SelectParam selectParam);

    /**
     * 查询所有资源
     *
     * @return
     */
    List<ResourceBaseVO> getAllResources();

    /**
     * 根据角色id查询资源
     *
     * @param roleId
     * @return
     */
    List<ResourceBaseVO> getResourcesByRoleId(Long roleId);

    /**
     * 新增资源
     *
     * @param editParam
     */
    void insertResource(EditParam editParam);

    /**
     * 更新资源
     *
     * @param editParam
     */
    void updateResource(EditParam editParam);

    /**
     * 删除资源
     *
     * @param ids
     */
    void deleteResources(List<Long> ids);

}
