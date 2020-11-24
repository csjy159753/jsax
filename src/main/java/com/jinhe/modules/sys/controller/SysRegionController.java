package com.jinhe.modules.sys.controller;


import com.jinhe.common.util.Result;
import com.jinhe.config.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.sys.dao.SysRegionMapper;
import com.jinhe.modules.sys.dto.SysRegionDTO;
import com.jinhe.modules.system.entity.SysRegion;
import com.jinhe.modules.sys.service.ISysRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 行政区编码 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/sys/sys-region")
@Api(tags = "sys")
@Transactional(rollbackFor = Exception.class)
public class SysRegionController {
    @Autowired
    private ISysRegionService sysRegionService;
    @Autowired
    private SysRegionMapper sysRegionMapper;
    Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 查询行政区列表
     *
     * @return
     */
    @ApiOperation(value = "查询行政区列表", notes = "查询行政区列表")
    @RequestMapping(value = "ListRegion", method = RequestMethod.GET)
    public Result<List<SysRegionDTO>> ListRegion(@RequestParam(required = false) String code) {
        List<SysRegionDTO> sysRegion;
        sysRegion = sysRegionMapper.listRegionCode(code);

        return ResultUtil.success(sysRegion);
    }

    /**
     * 新增行政区
     *
     * @return
     */
    @ApiOperation(value = "新增或更新行政区", notes = "新增或更新行政区")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public Result saveOrUpdate(@RequestBody SysRegion sysregion) {
        try {
            sysRegionService.saveOrUpdate(sysregion);
        } catch (Exception e) {
            log.error("saveOrUpdate", e.getMessage());
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
    @RequestMapping(value = "delRegion/{id}", method = RequestMethod.DELETE)
    public Result delRegion(@PathVariable String id) {
        try {
            sysRegionService.removeById(id);
        } catch (Exception e) {
            log.error("delRegion", e.getMessage());
            return ResultUtil.error(ResultEnum.REGION_EXIST_SUBSET_UNABLE_DEL);
        }
        return ResultUtil.success();
    }
}
