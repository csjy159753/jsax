package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.*;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.entity.PermissionItem;
import com.jinhe.modules.system.entity.SysPermission;
import com.jinhe.modules.system.service.ISysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class
SysPermissionController {

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ISysPermissionService iSysPer;

    /**
     * 用户新增权限
     **/
    @ApiOperation(value = "用户新增权限", notes = "用户新增权限")
    @RequestMapping(value = "AddByUser/{userId}", method = RequestMethod.POST)
    @SysLog(value = "AddByUser/{userId}")
    public Result addByUserId(@PathVariable String userId, @RequestParam List<String> resourceIds) {
        try {
            iSysPer.addByUserId(userId, resourceIds);
        } catch (Exception e) {
            logger.error("AddByUser", e.getMessage());
            return ResultUtil.error(ResultEnum.USER_INSERT_PERMISSION_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 机构新增权限
     **/
    @ApiOperation(value = "机构新增权限", notes = "机构新增权限")
    @RequestMapping(value = "AddByOrgan/{organId}", method = RequestMethod.POST)
    @SysLog(value = "AddByOrgan/{organId}")
    public Result addByOrganId(@PathVariable String organId, @RequestBody List<PermissionItem> permissionItem) {

        boolean flags = false;
        iSysPer.deleteByOrganId(organId);
        if (permissionItem != null && permissionItem.size() != 0 && !permissionItem.isEmpty())
            flags = iSysPer.addByOrganId(organId, permissionItem);
        else flags = true;
        if (flags == false) return ResultUtil.error(ResultEnum.ORGAN_INSERT_PERMISSIONS);
        return ResultUtil.success(flags);
    }

    /**
     * 角色新增权限
     **/
    @ApiOperation(value = "角色新增权限", notes = "角色新增权限")
    @RequestMapping(value = "AddByRoleId/{roleId}", method = RequestMethod.POST)
    @SysLog(value = "AddByRoleId/{roleId}")
    @Transactional
    public Result addByRoleId(@PathVariable String roleId, @RequestBody List<PermissionItem> permissionItem) {
        boolean flags = false;
        iSysPer.deleteByRoleId(roleId);
        if (permissionItem != null && permissionItem.size() != 0 && !permissionItem.isEmpty())
            flags = iSysPer.addByRoleId(roleId, permissionItem);
        else flags = true;
        if (flags == false) return ResultUtil.error(ResultEnum.ROLE_INSERT_PERMISSIONS);
        return ResultUtil.success(flags);
    }

    /**
     * 分页查询机构权限(父子级)
     **/
    @ApiOperation(value = "分页查询机构权限（树形结构）", notes = "分页查询机构权限（树形结构）")
    @RequestMapping(value = "ListByOrganId/{organId}", method = RequestMethod.GET)
    @SysLog(value = "ListByOrganId/{organId}")
    public Result listByOrganId(@PathVariable String organId, PageFilter pagefilter) {
        Page page = new Page(pagefilter.getStart(), pagefilter.getLength());
        List<TreeNode> pagelist = iSysPer.listByOrganId(organId);
        ListSub listSub = new ListSub();
        listSub.setTotal(pagelist.size());
        listSub.setList(pagelist);
        return ResultUtil.success(listSub);
    }

    /**
     * 分页查询机构权限(父子级)
     **/
    @ApiOperation(value = "分页查询角色权限（树形结构）", notes = "分页查询角色权限（树形结构）")
    @RequestMapping(value = "ListByRoleId/{roleId}", method = RequestMethod.GET)
    @SysLog(value = "ListByOrganId/{roleId}")
    public Result listByRoleId(@PathVariable String roleId, PageFilter pagefilter) {
        Page page = new Page(pagefilter.getStart(), pagefilter.getLength());
        List<TreeNode> pagelist = iSysPer.listByRoleId(roleId);
        ListSub listSub = new ListSub();
        listSub.setTotal(pagelist.size());
        listSub.setList(pagelist);
        return ResultUtil.success(listSub);
    }


    @ApiOperation(value = "新增权限", notes = "新增权限")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @SysLog(value = "saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysPermission sysPer) {
        try {
            iSysPer.saveOrUpdate(sysPer);
        } catch (Exception e) {
            logger.error("saveOrUpdate", e.getMessage());
            return ResultUtil.error(ResultEnum.NETWORK_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 根据Id查询权限
     **/
    @ApiOperation(value = "根据Id查询权限", notes = "根据Id查询权限")
    @RequestMapping(value = "Get/{id}", method = RequestMethod.GET)
    @SysLog(value = "Get/{id}")
    public Result getById(@PathVariable String id) {
        SysPermission sysPermission;
        try {
            sysPermission = iSysPer.getBaseMapper().selectById(id);
        } catch (Exception e) {
            logger.error("Get", e.getMessage());
            return ResultUtil.error(ResultEnum.PERMISSION_NOT_FOUND);
        }
        return ResultUtil.success(sysPermission);
    }

    /**
     * 根据Id删除权限
     **/
    @ApiOperation(value = "根据Id删除权限", notes = "根据Id删除权限")
    @RequestMapping(value = "Delete/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "Delete/{id}")
    public Result deleteById(@PathVariable String id) {
        SysPermission sysPermission;
        QueryWrapper<SysPermission> sysPermissionQueryWrapper = new QueryWrapper<>();
        sysPermissionQueryWrapper.eq("PARENT_ID", id);
        try {

            sysPermission = iSysPer.getBaseMapper().selectById(id);
            if (sysPermission == null) {
                return ResultUtil.error(ResultEnum.PERMISSION_NOT_FOUND);
            }

            if (iSysPer.getBaseMapper().selectCount(sysPermissionQueryWrapper) > 0) {
                return ResultUtil.error(ResultEnum.PERMISSION_EXIST_SUBSET_UNABLE_DEL);
            }

            iSysPer.removeById(id);
        } catch (Exception e) {
            logger.error("Delete", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }
}

