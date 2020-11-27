package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService

/**
 * <p>
 * 角色授权 服务类
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
interface ISysAppPermissionService : IService<SysAppPermission> {
      fun saveByRoleId(roleId: String, sysAppPermissions: List<SysAppPermission>)
}

