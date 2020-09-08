package com.jinhe.modules.system.controller;

import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUserOrgan;
import com.jinhe.modules.system.service.ISysOrganService;
import com.jinhe.modules.system.service.ISysUserOrganService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/SysUserOrgan")
@Api(description = "userOrgan", tags = {"system-SysUserOrgan"})
public class SysUserOrganController {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysUserOrganService sysUserOrganService;

    @Resource
    private ISysOrganService sysOrganService;

    /**
     * @return
     */
    @ApiOperation(value = "添加列表", notes = "添加列表")
    @RequestMapping(value = "/AddList", method = RequestMethod.POST)
    @SysLog(value = "AddList")
    public Result AddList(@RequestBody List<SysUserOrgan> organs) {
        try {
            sysUserOrganService.insertUserOrgan(organs);
        } catch (Exception e) {
            logger.error("AddList", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "添加机构", notes = "添加机构")
    @RequestMapping(value = "/AddOrgan", method = RequestMethod.POST)
    @SysLog(value = "AddOrgan")
    public Result AddOrgan(@RequestBody SysOrgan organ) {
        try {
            sysOrganService.getBaseMapper().insert(organ);
        } catch (Exception e) {
            logger.error("AddOrgan", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "新增中间表", notes = "新增中间表")
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    @SysLog(value = "Add")
    public Result Add(@RequestBody SysUserOrgan organ) {
        try {
            sysUserOrganService.getBaseMapper().insert(organ);
        } catch (Exception e) {
            logger.error("Add", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "Delete/{id}")
    public Result Delete(@PathVariable String id) {
        try {
            sysUserOrganService.removeById(id);
        } catch (Exception e) {
            logger.error("delete", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "查找", notes = "查找")
    @RequestMapping(value = "/Get/{id}", method = RequestMethod.GET)
    @SysLog(value = "Get/{id}")
    public Result Get(@PathVariable String id) {
        SysUserOrgan userOrgan;
        try {
            userOrgan = sysUserOrganService.getBaseMapper().selectById(id);
        } catch (Exception e) {
            logger.error("Get", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success(userOrgan);
    }
}