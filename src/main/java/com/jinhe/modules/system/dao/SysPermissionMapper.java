package com.jinhe.modules.system.dao;

import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户角色id查询资源菜单信息
     *
     * @param roleId 角色id
     * @return
     */
    List<SysResourceDTO> resourceIdByRoleId(String roleId);
}
