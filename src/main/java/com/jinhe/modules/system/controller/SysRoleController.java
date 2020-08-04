package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;

import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SysRole")
@Api(description = "角色操作", tags = "system-SysRole")
public class SysRoleController {

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 查询角色列表
     *
     * @return
     */
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @RequestMapping(value = "List/{id}", method = RequestMethod.GET)
    public Result List(@PathVariable String id, PageFilter filter, @RequestParam(required = false) String roleId) {
        IPage<SysRole> sysRole;
        try {
            Page page = new Page(filter.getStart(), filter.getLength());
            sysRole = sysRoleService.selectRoleList(page, id, roleId);
        } catch (Exception e) {
            logger.error("List", e.getMessage());
            return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
        }
        return ResultUtil.success(sysRole);
    }

    /**
     * 新增角色
     *
     * @return
     */
    @ApiOperation(value = "新增角色", notes = "新增角色")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @SysLog(value = "测试注解日志切面新增角色")
    public Result saveOrUpdate(@RequestBody SysRole sysRole) {
        try {
            sysRoleService.saveOrUpdate(sysRole);
        } catch (Exception e) {
            logger.error("saveOrUpdate", e.getMessage());
            return ResultUtil.error(ResultEnum.ROLE_UPDATE_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 更新角色
     *
     * @return
     */
    /*@ApiOperation(value = "更新角色", notes = "更新角色")
    @RequestMapping(value = "/UpdateRole", method = RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面更新角色")
    public Result updaRole(@RequestBody SysRole sysRole) {
        try {
            sysRoleService.getBaseMapper().updateById(sysRole);
        } catch (Exception e) {
            logger.error("UpdateRole", e.getMessage());
            return ResultUtil.error(ResultEnum.OBSOLETE);
        }
        return ResultUtil.success();
    }*/

    /**
     * 删除角色
     *
     * @return
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @RequestMapping(value = "/DeleteRole/{userId}/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "测试注解日志切面删除角色")
    public Result deleteRole(@PathVariable String userId, @PathVariable String id) {
        try {
            sysRoleService.deleteRole(id, userId);
        } catch (Exception e) {
            logger.error("DeleteRole", e.getMessage());
            return ResultUtil.error(ResultEnum.ROLE_ASSOCIATED_USERS);
        }
        return ResultUtil.success();
    }
}
