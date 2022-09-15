package com.hanfz.dao.mapper;

import com.hanfz.pojo.param.resource.EditParam;
import com.hanfz.pojo.param.resource.SelectParam;
import com.hanfz.pojo.vo.resource.ResourceBaseVO;
import com.hanfz.pojo.vo.resource.ResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2021-10-14 16:20
 */

@Mapper
public interface ResourceMapper {

    /**
     * 判断角色集合是否有当前资源权限
     *
     * @param roleIds 角色集合
     * @param url 请求路径
     * @param method 请求类型
     * @return 资源集合
     */
    Integer existAuthorize(@Param("roleIds") List<Long> roleIds,
                           @Param("url") String url,
                           @Param("method") String method);

    /**
     * 根据资源url和method判断资源是否存在-用于编辑时
     *
     * @param editParam
     * @return
     */
    Integer existResourceEdit(EditParam editParam);

    /**
     * 根据资源id集合统计资源-用于判断集合中是否有资源不存在
     *
     * @param resourceIds
     * @return
     */
    Integer countResourceByIds(@Param("resourceIds") List<Long> resourceIds);

    /**
     * 多条件统计资源总数
     *
     * @param selectParam
     * @return
     */
    Long countResource(SelectParam selectParam);

    /**
     * 多条件查询资源
     *
     * @param selectParam
     * @return
     */
    List<ResourceVO> getResources(SelectParam selectParam);

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
    List<ResourceBaseVO> getResourcesByRoleId(@Param("roleId") Long roleId);

    /**
     * 添加资源
     *
     * @param editParam
     */
    void insertResource(EditParam editParam);

    /**
     * 修改资源
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
