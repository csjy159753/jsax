package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.MapTree;
import com.jinhe.modules.system.entity.SysRole;
import com.jinhe.modules.system.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/system/sys-role")
@Slf4j
public class SysRoleController {
    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 查询角色列表
     *
     * @return
     */
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    public Result list(@PathVariable String userId) {
        try {
            List<SysRole> listRole = sysRoleService.list();
            List<ConcurrentHashMap<String, Object>> listMap = MapTree.CreateTree(listRole);
            return ResultUtil.success(listMap);
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
        }
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

            return ResultUtil.error(ResultEnum.ROLE_UPDATE_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 删除角色
     *
     * @return
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @RequestMapping(value = "/deleteRole/{userId}/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "测试注解日志切面删除角色")
    public Result deleteRole(@PathVariable String userId, @PathVariable String id) {
        SysRole sysRole;
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("PARENT_ID", id);
        try {
            sysRole = sysRoleService.getBaseMapper().selectById(id);
            if (sysRole == null) {
                return ResultUtil.error(ResultEnum.ROLE_NOT_FOUND);
            }
            if (sysRoleService.getBaseMapper().selectCount(sysRoleQueryWrapper) > 0) {
                return ResultUtil.error(ResultEnum.ROLE_EXIST_SUBSET_UNABLE_DEL);
            }
            sysRoleService.removeById(id);
        } catch (Exception e) {
            log.error("DeleteRole", e.getMessage());
            return ResultUtil.error(ResultEnum.ROLE_ASSOCIATED_USERS);
        }
        return ResultUtil.success();
    }
}
