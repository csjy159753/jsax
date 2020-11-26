package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.entity.SysAppPermission;
import com.jinhe.modules.system.service.ISysAppPermissionService;
import com.jinhe.modules.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Api(tags = "system")
@Transactional(rollbackFor = Exception.class)
public class SysAppPermissionController {
    @Autowired
    private ISysAppPermissionService iSysAppPermissionService;

    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 角色新增权限
     */
    @ApiOperation(value = "手机角色新增权限", notes = "手机角色新增权限")
    @RequestMapping(value = "addByRoleId/{roleId}", method = RequestMethod.POST)
    public Result addByRoleId(@PathVariable String roleId, @RequestBody List<SysAppPermission> sysAppPermissions) {
        if (iSysRoleService.getById(roleId) == null) {
            return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
        }
        if (sysAppPermissions != null && sysAppPermissions.size() > 0) {
            iSysAppPermissionService.saveByRoleId(roleId, sysAppPermissions);
        } else {
            return ResultUtil.error(ResultEnum.ROLE_INSERT_PERMISSIONS);
        }
        return ResultUtil.success();
    }

    /**
     * 角色新增权限
     */
    @ApiOperation(value = "手机角色移除权限", notes = "手机角色移除权限")
    @RequestMapping(value = "remove/{roleId}", method = RequestMethod.POST)
    public Result remove(@PathVariable String roleId) {
        if (iSysRoleService.getById(roleId) == null) {
            return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
        }
        UpdateWrapper updateWrapper = new UpdateWrapper<SysAppPermission>();
        updateWrapper.eq("role_id", roleId);
        iSysAppPermissionService.remove(updateWrapper);
        return ResultUtil.success();
    }
}
