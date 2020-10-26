package com.jinhe.modules.system.service;

import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.entity.PermissionItem;
import com.jinhe.modules.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
public interface ISysPermissionService extends IService<SysPermission> {

    boolean addByRoleId(String roleId, List<PermissionItem> permissionItem);

    List<TreeNode> listByOrganId(String orgionId);

    List<TreeNode> listByRoleId(String roleId);

    boolean add(SysPermission sysPer);

    SysPermission getById(String id);


    void deleteByRoleId(String roleId);

}
