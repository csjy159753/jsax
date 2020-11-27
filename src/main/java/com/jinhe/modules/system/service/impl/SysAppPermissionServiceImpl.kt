package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jinhe.modules.system.dao.SysAppPermissionMapper
import com.jinhe.modules.system.entity.SysPermission
import com.jinhe.modules.system.entity.SysPermissionItem
import com.jinhe.modules.system.service.ISysAppPermissionService
import org.springframework.stereotype.Service
import java.util.*

/**
 * <p>
 * 角色授权 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
@Service
class SysAppPermissionServiceImpl : ServiceImpl<SysAppPermissionMapper, SysAppPermission>(), ISysAppPermissionService {
    override fun saveByRoleId(roleId: String, sysAppPermissions: List<SysAppPermission>) {

        //----开始前先删除改角色菜单使用下面新的菜单设置了主外键级联删除 删除菜单授权item自定删除-----

        removeByIds(sysAppPermissions.map { d -> d.id })
        //----保存新菜单信息--------------------------
        //----保存新菜单信息--------------------------
        val sysPers: MutableList<SysPermission> = ArrayList()
        val sysPermissionItemList: MutableList<SysPermissionItem> = ArrayList()
        sysAppPermissions.forEach { d ->
            run {
                val sysAppPermission: SysAppPermission = SysAppPermission();
                sysAppPermission.roleId = roleId
                sysAppPermission.resourceId = d.resourceId
                this.save(sysAppPermission)
            }
        }
    }

}
