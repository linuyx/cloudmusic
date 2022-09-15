package com.hanfz;

import com.hanfz.pojo.param.resource.EditParam;
import com.hanfz.pojo.param.resource.SelectParam;
import com.hanfz.pojo.response.PageData;
import com.hanfz.pojo.response.ResponseData;
import com.hanfz.pojo.vo.resource.ResourceBaseVO;
import com.hanfz.pojo.vo.resource.ResourceVO;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-08-21 15:34
 */
public interface ResourceDubboService {

    /**
     * 多条件查询资源
     *
     * @param selectParam
     * @return
     */
    ResponseData<PageData<ResourceVO>> getResources(SelectParam selectParam);

    /**
     * 查询所有资源
     *
     * @return
     */
    ResponseData<List<ResourceBaseVO>> getAllResources();

    /**
     * 根据角色id查询资源id
     *
     * @param roleId
     * @return
     */
    ResponseData<List<ResourceBaseVO>> getResourcesByRoleId(Long roleId);

    /**
     * 新增资源
     *
     * @param editParam
     * @return
     */
    ResponseData insertResource(EditParam editParam);

    /**
     * 更新资源
     *
     * @param editParam
     * @return
     */
    ResponseData updateResource(EditParam editParam);

    /**
     * 删除资源
     *
     * @param ids
     * @return
     */
    ResponseData deleteResources(List<Long> ids);

}
