package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.*;
import com.jinhe.common.util.Tree.MapTree;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.entity.SysResourceItem;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysResourceItemService;
import com.jinhe.modules.system.service.ISysResourceService;
import com.jinhe.modules.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 资源菜单 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/system/sys-resource")
@Slf4j
public class SysResourceController {
    @Resource
    private ISysResourceService ISysResService;

    @Resource
    private ISysUserService iSysUserService;
    private Integer userType = 99;

    @Autowired
    ISysResourceItemService iSysResourceItemService;

    /**
     * 查询所有菜单（分页）
     **/
    @ApiOperation(value = "查询所有菜单（分页）", notes = "查询所有菜单")
    @RequestMapping(value = "List/{userId}", method = RequestMethod.GET)
    @SysLog(value = "List/{userId}")
    public Result List(@PathVariable String userId) {
        SysUser sysUser = iSysUserService.getById(userId);
        if (sysUser != null && userType.equals(sysUser.getType())) {
            List<SysResourceDTO> List = ISysResService.selectPageAll();
            List<ConcurrentHashMap<String, Object>> listMap = MapTree.CreateTree(List);
            return ResultUtil.success(listMap);
        } else {
            return ResultUtil.error(ResultEnum.RESOURCE_PERMISSION_DENIED);
        }
    }

    /**
     * 新增菜单
     **/
    @ApiOperation(value = "新增或更新菜单", notes = "新增或更新菜单")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @SysLog(value = "saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysResource sysResource) {
        try {
            ISysResService.saveOrUpdate(sysResource);
        } catch (Exception e) {
            log.error("saveOrUpdate", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCE_SAVE_UPDATE_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 根据Id查询资源
     **/
    @ApiOperation(value = "根据Id查询子项菜单", notes = "根据Id查询子项菜单")
    @RequestMapping(value = "listSysResourceItemById/{id}", method = RequestMethod.GET)
    @SysLog(value = "listSysResourceItemById/{id}")
    public Result listSysResourceItemById(@PathVariable String id) {
        try {
            QueryWrapper<SysResourceItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_id", id);
            List<SysResourceItem> list = iSysResourceItemService.list(queryWrapper);
            return ResultUtil.success(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_SELECT_NOT_FOUND);
        }
    }

    @ApiOperation(value = "新增或更新资源子项菜单", notes = "新增或更新资源子项菜单")
    @RequestMapping(value = "saveOrUpdateSysResourceItem", method = RequestMethod.POST)
    @SysLog(value = "saveOrUpdateSysResourceItem")
    public Result saveOrUpdateSysResourceItem(@RequestBody SysResourceItem sysRes) {
        try {
            iSysResourceItemService.saveOrUpdate(sysRes);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_INSERT_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "根据Id查询资源子项菜单", notes = "根据Id查询资源子项菜单")
    @RequestMapping(value = "GetSysResourceItem/{id}", method = RequestMethod.GET)
    @SysLog(value = "GetSysResourceItem/{id}")
    public Result GetSysResourceItem(@PathVariable String id) {
        SysResourceItem sysResourceItem;
        try {
            sysResourceItem = iSysResourceItemService.getBaseMapper().selectById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_SELECT_NOT_FOUND);
        }
        return ResultUtil.success(sysResourceItem);
    }

    /**
     * 根据Id删除权限
     **/
    @ApiOperation(value = "根据Id删除权限子项菜单", notes = "根据Id删除权限子项菜单")
    @RequestMapping(value = "deleteSysResourceItem/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "deleteSysResourceItem/{id}")
    public Result deleteSysResourceItem(@PathVariable String id) {
        try {
            iSysResourceItemService.removeById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_DELETE_ERROR);
        }
        return ResultUtil.success();
    }
}
