package com.jinhe.modules.system.service;

import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.PermissionItemDTO;
import com.jinhe.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
public interface ISysPermissionService extends IService<SysPermission> {


    /**
     * 保存该角色提交的菜单信息（注：会删除原先菜单绑定的授权信息 使用新加的）
     *
     * @param roleId
     * @param permissionItem
     * @return
     */
    boolean saveByRoleId(String roleId, List<PermissionItemDTO> permissionItem);

    /**
     * 根据角色id查询菜单权限
     *
     * @param roleId
     * @return
     */
    List<ConcurrentHashMap<String, Object>> listByRoleId(String roleId);
}
