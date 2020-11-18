package com.jinhe.modules.system.dao;

import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色授权 Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysResourceDTO> listByRoleId(String roleId);
}
