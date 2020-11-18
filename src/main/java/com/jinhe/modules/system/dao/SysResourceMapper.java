package com.jinhe.modules.system.dao;

import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源菜单 Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    /**
     * 查询所有资源菜单
     *
     * @return
     */
    List<SysResourceDTO> selectPageAll();

    /**
     * 根据用户查询资源菜单
     *
     * @param userId
     * @return
     */
    List<SysResourceDTO> listResource(String userId);

    /**
     * 获取全部菜单权限
     *
     * @param type
     * @return
     */
    List<SysResourceDTO> listResourceAdmin(Integer type);

    /**
     * 根据角色id获取菜单
     *
     * @param roleId
     * @return
     */
    List<SysResourceDTO> listByRole(String roleId);
}
