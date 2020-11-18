package com.jinhe.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.system.entity.SysAppResource;
import com.jinhe.modules.system.service.ISysAppResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/system/sys-app-resourse")
@Api(tags = "system")
@Transactional(rollbackFor = Exception.class)
public class SysAppResourceController {
    @Autowired
    private ISysAppResourceService iSysAppResourceService;

    @ApiOperation(value = "手机角色新增权限", notes = "角色新增权限")
    @RequestMapping(value = "saveOrUpdate/{roleId}", method = RequestMethod.POST)
    public Result saveOrUpdate(@PathVariable String roleId, @RequestBody SysAppResource sysAppResource) {
        iSysAppResourceService.saveOrUpdate(sysAppResource);
        return ResultUtil.success();
    }


    @ApiOperation(value = "删除手机资源菜单", notes = "删除手机资源菜单")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {
        iSysAppResourceService.removeById(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "获取手机资源菜单", notes = "删除手机资源菜单")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<ListSub<SysAppResource>> list(PageFilter pageFilter) {
        IPage page = new Page(pageFilter.getStart(), pageFilter.getLength());
        QueryWrapper queryWrapper = new QueryWrapper<SysAppResource>();
        queryWrapper.orderByDesc("create_time");
        IPage<SysAppResource> iPage = iSysAppResourceService.page(page, queryWrapper);
        return ResultUtil.success(iPage);
    }
}
