package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.*;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.PermissionItemDTO;
import com.jinhe.modules.system.entity.SysPermission;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.service.ISysPermissionService;
import com.jinhe.modules.system.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SysPermission")
@Api(description = "权限", tags = "system-SysPermission")
@Slf4j
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

    /**
     * 分页查询机构权限(父子级)
     **/
    @ApiOperation(value = "分页查询角色权限（树形结构）", notes = "分页查询角色权限（树形结构）")
    @RequestMapping(value = "listByRoleId/{roleId}", method = RequestMethod.GET)
    @SysLog(value = "listByRoleId/{roleId}")
    public Result listByRoleId(@PathVariable String roleId) {
        SysRole sysRole = iSysRoleService.getById(roleId);
        if (sysRole == null) {
            return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
        }
        List<ConcurrentHashMap<String, Object>> pageList = iSysPer.listByRoleId(roleId);
        return ResultUtil.success(pageList);
    }
}

