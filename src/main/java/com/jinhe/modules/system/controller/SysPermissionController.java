package com.jinhe.modules.system.controller;


import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.dto.PermissionItemDTO;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.service.ISysPermissionService;
import com.jinhe.modules.system.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class SysPermissionController {

    @Autowired
    private ISysPermissionService iSysPer;

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
            iSysPer.saveByRoleId(roleId, permissionItem);
        } else {
            return ResultUtil.error(ResultEnum.ROLE_INSERT_PERMISSIONS);
        }
        return ResultUtil.success(true);
    }

}
