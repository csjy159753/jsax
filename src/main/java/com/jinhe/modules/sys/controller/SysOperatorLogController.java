package com.jinhe.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.*;
import com.jinhe.modules.sys.service.ISysOperatorLogService;
import com.jinhe.modules.system.entity.SysOperatorLog;
import com.jinhe.modules.system.entity.SysOrgan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/sys/sys-operator-log")
@Api(tags = "sys")
@Transactional(rollbackFor = Exception.class)
public class SysOperatorLogController {
    @Autowired
    private ISysOperatorLogService iSysOperatorLogService;

    /**
     * 操作日志列表
     **/
    @ApiOperation(value = "操作日志列表", notes = "操作日志列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<ListSub<SysOperatorLog>> list(PageFilter pageFilter, String moduleName, String moduleType) {
        QueryWrapper<SysOperatorLog> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SysOperatorLog::getModuleName, moduleName).like(SysOperatorLog::getModuleType, moduleType);
        IPage<SysOperatorLog> iPage = iSysOperatorLogService.page(new Page(pageFilter.getStart(), pageFilter.getLength()), queryWrapper);
        return ResultUtil.success(iPage);
    }

    /**
     * 操作日志列表
     **/
    @ApiOperation(value = "操作日志列表", notes = "操作日志列表")
    @RequestMapping(value = "listModuleName", method = RequestMethod.GET)
    public Result<List<String>> listModuleName(String moduleType) {
        QueryWrapper<SysOperatorLog> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().groupBy(SysOperatorLog::getModuleName).select(SysOperatorLog::getModuleName);
        if (!StringUtils.isEmpty(moduleType)) {
            queryWrapper.lambda().like(SysOperatorLog::getModuleType, moduleType);
        }
        List<String> list = iSysOperatorLogService.listObjs(queryWrapper, a -> a.toString());
        return ResultUtil.success(list);
    }

    /**
     * 操作日志列表
     **/
    @ApiOperation(value = "操作日志列表", notes = "操作日志列表")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Result<SysOperatorLog> get(@PathVariable String id) {
        SysOperatorLog sysOperatorLog = iSysOperatorLogService.getById(id);
        return ResultUtil.success(sysOperatorLog);
    }
}

