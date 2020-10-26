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
    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    @SysLog(value = "addList")
    public Result addList(@RequestBody List<SysUserOrgan> organs) {
        try {
            sysUserOrganService.insertUserOrgan(organs);
        } catch (Exception e) {
            logger.error("addList", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "添加机构", notes = "添加机构")
    @RequestMapping(value = "/addOrgan", method = RequestMethod.POST)
    @SysLog(value = "addOrgan")
    public Result addOrgan(@RequestBody SysOrgan organ) {
        try {
            sysOrganService.getBaseMapper().insert(organ);
        } catch (Exception e) {
            logger.error("addOrgan", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "新增中间表", notes = "新增中间表")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SysLog(value = "add")
    public Result add(@RequestBody SysUserOrgan organ) {
        try {
            sysUserOrganService.getBaseMapper().insert(organ);
        } catch (Exception e) {
            logger.error("Add", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "delete/{id}")
    public Result delete(@PathVariable String id) {
        try {
            sysUserOrganService.removeById(id);
        } catch (Exception e) {
            logger.error("delete", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation(value = "查找", notes = "查找")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @SysLog(value = "get/{id}")
    public Result get(@PathVariable String id) {
        SysUserOrgan userOrgan;
        try {
            userOrgan = sysUserOrganService.getBaseMapper().selectById(id);
        } catch (Exception e) {
            logger.error("get", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultUtil.success(userOrgan);
    }
}