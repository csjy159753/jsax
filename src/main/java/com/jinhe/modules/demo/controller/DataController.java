package com.jinhe.modules.demo.controller;


import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.config.ConfigProperty;
import com.jinhe.modules.demo.entity.Data;
import com.jinhe.modules.demo.service.IDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-12-04
 */
@RestController
@Api(tags = {"demo"})
@RequestMapping("/demo/dataMock")
public class DataController {
    @Autowired
    private IDataService iDataService;

    /**
     * 数据模拟
     *
     * @return
     */
    @ApiOperation(value = "获取数据", notes = "获取数据")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SysLog(value = "当前版本version")
    public Result list(String id) {
        return ResultUtil.success(iDataService.getById(id));
    }

    /**
     * 数据模拟
     *
     * @return
     */
    @ApiOperation(value = "获取数据", notes = "获取数据")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @SysLog(value = "saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Data data) {
        return ResultUtil.success(iDataService.saveOrUpdate(data));
    }
}

