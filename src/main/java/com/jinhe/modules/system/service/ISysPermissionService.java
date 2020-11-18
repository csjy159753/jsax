package com.jinhe.modules.system.service;

import com.jinhe.modules.system.dto.PermissionItemDTO;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色授权 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 根据用户角色保存 菜单权限
     *
     * @param roleId
     * @param permissionItem
     */
    void saveByRoleId(String roleId, List<PermissionItemDTO> permissionItem);

    /**
     * 根据角色id获取菜单权限
     * @param roleId
     * @return
     */
    List<SysResourceDTO> listByRoleId(String roleId);
}
