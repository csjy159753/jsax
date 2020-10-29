package com.jinhe.modules.system.controller;

import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.system.dto.SysRegionDto;
import com.jinhe.modules.system.entity.SysRegion;
import com.jinhe.modules.system.service.ISysRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SysRegion")
@Api(description = "行政区", tags = "system-SysRegion")
public class SysRegionController {

    /**
     * 记录器
     */
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysRegionService sysRegionService;

    /**
     * 查询行政区列表
     *
     * @return
     */
    @ApiOperation(value = "查询行政区列表", notes = "查询行政区列表")
    @RequestMapping(value = "ListRegion", method = RequestMethod.GET)
    @SysLog(value = "ListRegion")
    public Result regionListTree(@RequestParam(required = false) String code) {
        List<SysRegionDto> sysRegion;
        try {
            sysRegion = sysRegionService.selectRegionList(code);
        } catch (Exception e) {
            logger.error("ListRegion", e.getMessage());
            return ResultUtil.success(ResultEnum.REGION_NOT_FOUND);
        }
        return ResultUtil.success(sysRegion);
    }

    /**
     * 行政区编码获取详情
     *
     * @return
     */
    @ApiOperation(value = "行政区编码获取详情", notes = "行政区编码获取详情")
    @RequestMapping(value = "RegionForCode", method = RequestMethod.GET)
    @SysLog(value = "RegionForCode")
    public Result RegionForCode(@RequestParam(required = false) String code) {
        SysRegion sysRegion = sysRegionService.regionForCode(code);
        return ResultUtil.success(sysRegion);
    }

    /**
     * 新增行政区
     *
     * @return
     */
    @ApiOperation(value = "新增或更新行政区", notes = "新增或更新行政区")
    @SysLog(value = "测试注解日志切面新增行政区")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public Result saveOrUpdate(@RequestBody SysRegion sysregion) {
        try {
            sysRegionService.saveOrUpdate(sysregion);
        } catch (Exception e) {
            logger.error("saveOrUpdate", e.getMessage());
            return ResultUtil.error(ResultEnum.REGION_INSERT_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 删除行政区
     *
     * @return
     */
    @ApiOperation(value = "删除行政区", notes = "删除行政区")
    @SysLog(value = "测试注解日志切面删除行政区")
    @RequestMapping(value = "delRegion/{id}", method = RequestMethod.DELETE)
    public Result delRegion(@PathVariable String id) {
        try {
            sysRegionService.removeById(id);
        } catch (Exception e) {
            logger.error("delRegion", e.getMessage());
            return ResultUtil.error(ResultEnum.REGION_EXIST_SUBSET_UNABLE_DEL);
        }
        return ResultUtil.success();
    }

}

