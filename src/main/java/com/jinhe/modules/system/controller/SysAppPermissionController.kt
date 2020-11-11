package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper
import com.jinhe.common.util.Result
import com.jinhe.common.util.ResultUtil
import com.jinhe.config.ResultEnum
import com.jinhe.modules.system.entity.SysAppPermission
import com.jinhe.modules.system.service.ISysAppPermissionService
import com.jinhe.modules.system.service.ISysRoleService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 角色授权 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/system/sys-app-permission")
@Transactional(rollbackFor = [Exception::class])
class SysAppPermissionController {

    @Autowired
    private lateinit var iSysAppPermissionService: ISysAppPermissionService

    @Autowired
    private lateinit var iSysRoleService: ISysRoleService

    /**
     * 角色新增权限
     */
    @ApiOperation(value = "手机角色新增权限", notes = "手机角色新增权限")
    @RequestMapping(value = ["addByRoleId/{roleId}"], method = [RequestMethod.POST])
    fun addByRoleId(@PathVariable roleId: String, @RequestBody sysAppPermissions: List<SysAppPermission>): Result<*> {
        iSysRoleService!!.getById(roleId) ?: return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND)
        if (sysAppPermissions != null && sysAppPermissions.isNotEmpty()) {
            iSysAppPermissionService.saveByRoleId(roleId, sysAppPermissions)
        } else {
            return ResultUtil.error(ResultEnum.ROLE_INSERT_PERMISSIONS)
        }
        return ResultUtil.success()
    }

    /**
     * 角色新增权限
     */
    @ApiOperation(value = "手机角色移除权限", notes = "手机角色移除权限")
    @RequestMapping(value = ["remove/{roleId}"], method = [RequestMethod.POST])
    fun remove(@PathVariable roleId: String): Result<*> {
        val sysRole = iSysRoleService!!.getById(roleId) ?: return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND)
        val updateWrapper = UpdateWrapper<SysAppPermission>()
        updateWrapper.eq("role_id", roleId)
        iSysAppPermissionService!!.remove(updateWrapper)
        return ResultUtil.success()
    }
}

