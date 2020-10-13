package com.jinhe.modules.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.demo.dto.DStudentDto;
import com.jinhe.modules.demo.entity.DStudent;
import com.jinhe.modules.demo.service.IDStudentService;
import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.modules.system.service.ISysLog2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-11
 */
@RestController
@RequestMapping("/demo/d-student")
@Api(description = "演示demo学生类", tags = "demo-d-student")
public class DStudentController {

    @Autowired
    private IDStudentService idStudentService;
    @Autowired
    private ISysLog2Service iSysLog2Service;

    /**
     * 插叙列表
     */
    @ApiOperation(value = "List分页测试", notes = "List分页测试")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @com.jinhe.common.annotation.SysLog(value = "list")
    public Result List(PageFilter filter) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", "张三");
        Page page = new Page(filter.getStart(), filter.getLength());
        IPage<DStudent> pageData = idStudentService.page(page, queryWrapper);
        return ResultUtil.success(pageData);
    }

    /**
     * 插叙列表
     */
    @ApiOperation(value = "List分页测试")
    @RequestMapping(value = "ListC", method = RequestMethod.GET)
    @com.jinhe.common.annotation.SysLog(value = "ListC")
    public Result ListC(PageFilter filter, String name) {

        Page page = new Page(filter.getStart(), filter.getLength());

        IPage<DStudent> pageData = idStudentService.getListbyName(page, name);
        return ResultUtil.success(pageData);
    }

    /**
     * 插叙列表
     */
    @ApiOperation(value = "List分页测试")
    @RequestMapping(value = "ListDlyCa", method = RequestMethod.GET)
    @com.jinhe.common.annotation.SysLog(value = "ListDlyCa")
    public Result ListDly() {
        return ResultUtil.success(iSysLog2Service.listAll());
    }

    /**
     * 插叙列表
     */
    @ApiOperation(value = "List分页测试")
    @RequestMapping(value = "ListDly", method = RequestMethod.GET)
    @com.jinhe.common.annotation.SysLog(value = "ListDly")
    public Result ListDly(PageFilter filter, String name) {

        Page page = new Page(filter.getStart(), filter.getLength());

        IPage<DStudentDto> pageData = idStudentService.ListDly(page, name);
        return ResultUtil.success(pageData);
    }
}

