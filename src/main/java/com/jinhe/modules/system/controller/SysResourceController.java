package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.*;
import com.jinhe.common.util.Tree.MapTree;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.entity.SysResourceItem;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysResourceItemService;
import com.jinhe.modules.system.service.ISysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
@Api(tags = "system")
@Transactional(rollbackFor = Exception.class)
public class SysResourceController {
    @Resource
    private ISysResourceService ISysResService;

    @Resource
    private ISysUserService iSysUserService;

    private Integer userType = 99;

    @Autowired
    ISysResourceItemService iSysResourceItemService;
    Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 查询所有菜单
     **/
    @ApiOperation(value = "查询所有菜单根据权限查询", notes = "查询所有菜单根据权限查询")
    @RequestMapping(value = "listResource/{userId}", method = RequestMethod.GET)
    @SysLog(value = "listResource/{userId}")
    public Result<List<SysResourceDTO>> listResource(@PathVariable String userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<SysResourceDTO> sysResource = ISysResService.listResource(userId);
        List<ConcurrentHashMap<String, Object>> listMap = MapTree.CreateTree(sysResource);
        return ResultUtil.success(listMap);
    }

    /**
     * 查询所有菜单
     **/
    @ApiOperation(value = "查询所有菜单管理员专用", notes = "查询所有菜单管理员专用")
    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    public Result<List<SysResourceDTO>> List(@PathVariable String userId) {
        SysUser sysUser = iSysUserService.getById(userId);
        if (sysUser != null && userType.equals(sysUser.getType())) {
            List<SysResourceDTO> List = ISysResService.listResourceAdmin();
            List<ConcurrentHashMap<String, Object>> listMap = MapTree.CreateTree(List);
            return ResultUtil.success(listMap);
        } else {
            return ResultUtil.error(ResultEnum.RESOURCE_PERMISSION_DENIED);
        }
    }
    /**
     * 根据角色id获取菜单菜单管理员专用
     **/
    @ApiOperation(value = "根据角色id获取菜单菜单管理员专用", notes = "根据角色id获取菜单菜单管理员专用")
    @RequestMapping(value = "listByRole/{roleId}", method = RequestMethod.GET)
    public Result<List<SysResourceDTO>> listByRole(@PathVariable String roleId) {
        List<SysResourceDTO> List = ISysResService.listByRole(roleId);
        List<ConcurrentHashMap<String, Object>> listMap = MapTree.CreateTree(List);
        return ResultUtil.success(listMap);
    }
    /**
     * 新增菜单
     **/
    @ApiOperation(value = "新增或更新菜单", notes = "新增或更新菜单")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
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
    @RequestMapping(value = "listSysResourceItem/{resourceId}", method = RequestMethod.GET)
    public Result<List<SysResourceItem>> listSysResourceItemById(@PathVariable String resourceId) {
        try {
            QueryWrapper<SysResourceItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_id", resourceId);
            List<SysResourceItem> list = iSysResourceItemService.list(queryWrapper);
            return ResultUtil.success(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_SELECT_NOT_FOUND);
        }
    }

    @ApiOperation(value = "新增或更新资源子项菜单", notes = "新增或更新资源子项菜单")
    @RequestMapping(value = "saveOrUpdateSysResourceItem", method = RequestMethod.POST)
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
    @RequestMapping(value = "getSysResourceItem/{id}", method = RequestMethod.GET)
    public Result<SysResourceItem> GetSysResourceItem(@PathVariable String id) {
        SysResourceItem sysResourceItem;
        try {
            sysResourceItem = iSysResourceItemService.getBaseMapper().selectById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_SELECT_NOT_FOUND);
        }
        return ResultUtil.success(sysResourceItem);
    }

    @ApiOperation(value = "删除资源菜单", notes = "删除资源菜单")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {
        ISysResService.removeById(id);
        return ResultUtil.success();
    }

    /**
     * 根据Id删除权限
     **/
    @ApiOperation(value = "根据Id删除权限子项菜单", notes = "根据Id删除权限子项菜单")
    @RequestMapping(value = "deleteSysResourceItem/{id}", method = RequestMethod.DELETE)
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
