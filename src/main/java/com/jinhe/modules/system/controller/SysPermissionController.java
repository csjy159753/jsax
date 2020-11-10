package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.dto.PermissionItemDTO;
import com.jinhe.modules.system.entity.SysPermission;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.service.ISysPermissionService;
import com.jinhe.modules.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色授权 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/system/sys-permission")
@Api(tags = "system")
public class SysPermissionController {

    @Autowired
    private ISysPermissionService iSysPermissionService;

    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 角色新增权限
     **/
    @ApiOperation(value = "角色新增权限", notes = "角色新增权限")
    @RequestMapping(value = "addByRoleId/{roleId}", method = RequestMethod.POST)
    @SysLog(value = "addByRoleId")
    public Result addByRoleId(@PathVariable String roleId, @RequestBody List<PermissionItemDTO> permissionItem) {
        SysRole sysRole = iSysRoleService.getById(roleId);
        if (sysRole == null) {
            return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
        }
        if (permissionItem != null && permissionItem.size() != 0 && !permissionItem.isEmpty()) {
            iSysPermissionService.saveByRoleId(roleId, permissionItem);
        } else {
            return ResultUtil.error(ResultEnum.ROLE_INSERT_PERMISSIONS);
        }
        return ResultUtil.success(true);
    }

    /**
     * 角色新增权限
     **/
    @ApiOperation(value = "角色新增权限", notes = "角色新增权限")
    @RequestMapping(value = "remove/{roleId}", method = RequestMethod.POST)
    @SysLog(value = "remove")
    public Result remove(@PathVariable String roleId) {
        SysRole sysRole = iSysRoleService.getById(roleId);
        if (sysRole == null) {
            return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
        }
        UpdateWrapper<SysPermission> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("role_id", roleId);
        iSysPermissionService.remove(updateWrapper);
        return ResultUtil.success(true);
    }
}
