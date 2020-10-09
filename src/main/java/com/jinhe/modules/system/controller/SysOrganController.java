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
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.service.ISysOrganService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiak
 * @since 2020-04-16
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SysOrgan")
@Api(description = "机构管理", tags = {"system-SysOrgan"})
public class SysOrganController {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysOrganService iSysOrganService;

    /**
     * 查询所有机构（分页）
     **/
    @RequestMapping(value = "sys_organ", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有机构（分页）", notes = "查询所有机构")
    @SysLog(value = "sys_organ")
    public Result pageSysOrgan(PageFilter filter) {
        IPage<SysOrgan> sysOrganIPage;
        try {
            Page page = new Page(filter.getStart(), filter.getLength());
            sysOrganIPage = iSysOrganService.page(page);
        } catch (Exception e) {
            logger.error("sys_organ", e.getMessage());
            return ResultUtil.error(ResultEnum.ORGAN_NOT_FOUND);
        }
        return ResultUtil.success(sysOrganIPage);
    }

    /**
     * 根据ID查询机构
     **/
    @ApiOperation(value = "根据ID查询机构", notes = "根据ID查询机构")
    @RequestMapping(value = "Get/{id}", method = RequestMethod.GET)
    @SysLog(value = "Get/{id}")
    public Result SelectSysOrganID(@PathVariable String id) {
        SysOrgan sysOrgan;
        try {
            sysOrgan = iSysOrganService.getBaseMapper().selectById(id);
        } catch (Exception e) {
            logger.error("Get", e.getMessage());
            return ResultUtil.error(ResultEnum.ORGAN_NOT_FOUND);
        }
        return ResultUtil.success(sysOrgan);
    }

    /**
     * 新增机构
     **/
    @ApiOperation(value = "新增机构", notes = "新增机构")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @SysLog(value = "saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysOrgan sysOrgan) {
        try {
            iSysOrganService.saveOrUpdate(sysOrgan);
        } catch (Exception e) {
            logger.error("saveOrUpdate", e.getMessage());
            return ResultUtil.error(ResultEnum.NETWORK_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 分页查询机构(父子级)
     **/
    @ApiOperation(value = "查询所有机构（树形结构）", notes = "查询所有机构（树形结构）")
    @RequestMapping(value = "List/{userId}", method = RequestMethod.GET)
    @SysLog(value = "List/{userId}")
    public Result SelectOrganParent(PageFilter filter, @PathVariable String userId, @RequestParam(required = false) String organId) {
        IPage<SysOrgan> sysOrganIPage;
        try {
            Page page = new Page(filter.getStart(), filter.getLength());
            sysOrganIPage = iSysOrganService.SelectOrgan(page, userId, organId);
        } catch (Exception e) {
            logger.error("List", e.getMessage());
            return ResultUtil.error();
        }
        return ResultUtil.success(sysOrganIPage);
    }

    @ApiOperation(value = "根据ID删除机构", notes = "根据ID删除机构")
    @RequestMapping(value = "DeleteByOrganId/{userId}/{id}", method = RequestMethod.DELETE)
    public Result DeleteOrganByOrganId(@PathVariable String id, @PathVariable String userId) {
        SysOrgan sysOrgan;
        QueryWrapper<SysOrgan> sysOrganQueryWrapper = new QueryWrapper<>();
        sysOrganQueryWrapper.eq("PARENT_ID", id);
        sysOrgan = iSysOrganService.getBaseMapper().selectById(id);
        if (sysOrgan == null) {
            return ResultUtil.error(ResultEnum.ORGAN_NOT_FOUND);
        }
        if (iSysOrganService.getBaseMapper().selectCount(sysOrganQueryWrapper) > 0) {
            return ResultUtil.error(ResultEnum.ORGAN_EXIST_SUBSET_UNABLE_DEL);
        }
        iSysOrganService.DeleteOrganByOrganId(userId, id);
        return ResultUtil.success();
    }

    @RequestMapping(value = "GetOrganSubset", method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取全部子集", notes = "根据ID获取全部子集")
    public Result GetOrganSubset(@RequestBody List<String> organIds) {
        List<ConcurrentHashMap<String, Object>> concurrentHashMaps;
        concurrentHashMaps = MapTree.CreateTree(iSysOrganService.GetOrganSubset(organIds));
        return ResultUtil.success(concurrentHashMaps);
    }
}

