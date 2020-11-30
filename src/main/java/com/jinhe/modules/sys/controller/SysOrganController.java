package com.jinhe.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.config.LongSwingConstants;
import com.jinhe.common.util.*;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.sys.dto.SysOrganDTO;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.sys.service.ISysOrganService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 组织机构 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/sys/sys-organ")
@Api(tags = "sys")
@Transactional(rollbackFor = Exception.class)
public class SysOrganController {
    @Resource
    private ISysOrganService iSysOrganService;
    @Resource
    private ISysUserService iSysUserService;


    /**
     * 根据ID查询机构
     **/
    @ApiOperation(value = "根据ID查询机构", notes = "根据ID查询机构")
    @RequestMapping(value = "getSysOrganId/{id}", method = RequestMethod.GET)
    public Result<SysOrgan> getSysOrganId(@PathVariable String id) {
        SysOrgan sysOrgan;
        sysOrgan = iSysOrganService.getById(id);
        return ResultUtil.success(sysOrgan);
    }

    /**
     * 新增机构
     **/
    @ApiOperation(value = "新增机构", notes = "新增机构")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public Result saveOrUpdate(@RequestBody SysOrgan sysOrgan) {
        if (sysOrgan == null) {
            return ResultUtil.error();
        }

        if (sysOrgan.getType() != null && !sysOrgan.getType().equals(LongSwingConstants.Number.ONE)
                && !sysOrgan.getType().equals(LongSwingConstants.Number.ZERO)) {
            return ResultUtil.error(ResultEnum.ORGAN_TYPE_ERROR);
        }
        if (sysOrgan.getType() == null) {
            sysOrgan.setType(LongSwingConstants.Number.ONE);
        }
        if (!StringUtils.isEmpty(sysOrgan.getTag())) {
            QueryWrapper<SysOrgan> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysOrgan::getType, sysOrgan.getType());
            int count = iSysOrganService.count(queryWrapper);
            if (count > 0) {
                return ResultUtil.error(ResultEnum.ORGAN_TAG_REPEAT);
            }
        }
        iSysOrganService.saveOrUpdate(sysOrgan);
        iSysOrganService.saveOrUpdateChildrenNumAndLevel(sysOrgan);
        return ResultUtil.success();
    }

    /**
     * 根据机构id查询下级组织机构 树形结构分级查询
     **/
    @ApiOperation(value = "根据机构id查询下级组织机构 树形结构分级查询", notes = "根据机构id查询下级组织机构 树形结构分级查询")
    @RequestMapping(value = "selectOrganByOrganId/{userId}", method = RequestMethod.GET)
    public Result<ListSub<SysOrgan>> selectOrganByOrganId(@PathVariable String userId, Integer type, String organId, PageFilter pageFilter) {

        SysUser sysUser = iSysUserService.getById(userId);
        if (StringUtils.isEmpty(organId) && !sysUser.getType().equals(LongSwingConstants.USER_TYPE_ADMIN)) {
            return ResultUtil.error();
        }
        QueryWrapper<SysOrgan> queryWrapper = new QueryWrapper();
        if (organId == null) {
            queryWrapper.lambda().isNull(SysOrgan::getParentId);
        } else {
            queryWrapper.lambda().eq(SysOrgan::getParentId, organId);
        }
        if (type == null) {
            type = LongSwingConstants.Number.ZERO;
        }
        queryWrapper.lambda().eq(SysOrgan::getType, type);
        IPage<SysOrgan> iPage = iSysOrganService.page(new Page(pageFilter.getStart(), pageFilter.getLength()), queryWrapper);

        return ResultUtil.success(iPage);
    }


    @ApiOperation(value = "根据ID删除机构", notes = "根据ID删除机构")
    @RequestMapping(value = "remove/{userId}/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id, @PathVariable String userId) {
        SysUser sysUser = iSysUserService.getById(userId);
        if (sysUser == null || !LongSwingConstants.USER_TYPE_ADMIN.equals(sysUser.getType())) {
            return ResultUtil.error(ResultEnum.RESOURCE_PERMISSION_DENIED);
        }
        SysOrgan sysOrgan = iSysOrganService.getById(id);
        if (sysOrgan == null) {
            return ResultUtil.error(ResultEnum.ORGAN_NOT_FOUND);
        }
        QueryWrapper<SysOrgan> sysOrganQueryWrapper = new QueryWrapper<>();
        sysOrganQueryWrapper.lambda().eq(SysOrgan::getParentId, id);
        if (iSysOrganService.count(sysOrganQueryWrapper) > 0) {
            return ResultUtil.error(ResultEnum.ORGAN_EXIST_SUBSET_UNABLE_DEL);
        }
        iSysOrganService.removeById(id);
        iSysOrganService.saveOrUpdateChildrenNumAndLevel(sysOrgan.getParentId());
        return ResultUtil.success();
    }
}
