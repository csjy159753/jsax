package com.jinhe.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    private Integer userType = 99;
    private Integer userTypeAdmin = 98;

    /**
     * 根据ID查询机构
     **/
    @ApiOperation(value = "根据ID查询机构", notes = "根据ID查询机构")
    @RequestMapping(value = "selectSysOrganId/{id}", method = RequestMethod.GET)
    public Result<SysOrgan> selectSysOrganId(@PathVariable String id) {
        SysOrgan sysOrgan;
        sysOrgan = iSysOrganService.getBaseMapper().selectById(id);
        return ResultUtil.success(sysOrgan);
    }

    /**
     * 新增机构
     **/
    @ApiOperation(value = "新增机构", notes = "新增机构")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public Result saveOrUpdate(@RequestBody SysOrgan sysOrgan) {
        if (sysOrgan.getRegionCode() != null) {
            sysOrgan.setDepth(RegionUtil.maxLevel(sysOrgan.getRegionCode()));
        }
        if (sysOrgan.getType() != null && sysOrgan.getType() != 0 && sysOrgan.getType() != 1) {
            return ResultUtil.error(ResultEnum.ORGAN_TYPE_ERROR);
        }
        if (sysOrgan.getType() == null) {
            sysOrgan.setType(0);
        }
        if (!StringUtils.isEmpty(sysOrgan.getTag())) {
            QueryWrapper<SysOrgan> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tag", sysOrgan.getTag());
            int count = iSysOrganService.count(queryWrapper);
            if (count > 0) {
                return ResultUtil.error(ResultEnum.ORGAN_TAG_REPEAT);
            }
        }
        iSysOrganService.saveOrUpdate(sysOrgan);
        return ResultUtil.success();
    }

    /**
     * 根据机构id查询下级组织机构 树形结构分级查询
     **/
    @ApiOperation(value = "根据机构id查询下级组织机构 树形结构分级查询", notes = "根据机构id查询下级组织机构 树形结构分级查询")
    @RequestMapping(value = "selectOrganByOrganId/{userId}", method = RequestMethod.GET)
    public Result<List<SysOrganDTO>> selectOrganByOrganId(@PathVariable String userId, String organId) {
        SysUser sysUser = iSysUserService.getById(userId);
        if (StringUtils.isEmpty(organId) && !sysUser.getType().equals(userTypeAdmin)) {
            return ResultUtil.error();
        }

        List<SysOrganDTO> sysOrganIPage = iSysOrganService.selectOrganByOrganId(organId);
        return ResultUtil.success(sysOrganIPage);
    }

    @ApiOperation(value = "根据ID删除机构", notes = "根据ID删除机构")
    @RequestMapping(value = "removeOrganByOrganId/{userId}/{id}", method = RequestMethod.DELETE)
    public Result removeOrganByOrganId(@PathVariable String id, @PathVariable String userId) {
        SysUser sysUser = iSysUserService.getById(userId);
        if (sysUser == null || !userType.equals(sysUser.getType())) {
            return ResultUtil.error(ResultEnum.RESOURCE_PERMISSION_DENIED);
        }
        QueryWrapper<SysOrgan> sysOrganQueryWrapper = new QueryWrapper<>();
        sysOrganQueryWrapper.eq("PARENT_ID", id);
        SysOrgan sysOrgan = iSysOrganService.getBaseMapper().selectById(id);
        if (sysOrgan == null) {
            return ResultUtil.error(ResultEnum.ORGAN_NOT_FOUND);
        }
        if (iSysOrganService.getBaseMapper().selectCount(sysOrganQueryWrapper) > 0) {
            return ResultUtil.error(ResultEnum.ORGAN_EXIST_SUBSET_UNABLE_DEL);
        }
        iSysOrganService.removeById(id);
        return ResultUtil.success();
    }
//
//    @RequestMapping(value = "GetOrganSubset", method = RequestMethod.POST)
//    @ApiOperation(value = "根据ID获取全部子集", notes = "根据ID获取全部子集")
//    public Result GetOrganSubset(@RequestBody List<String> organIds) {
//        List<ConcurrentHashMap<String, Object>> concurrentHashMaps;
//        concurrentHashMaps = MapTree.CreateTree(iSysOrganService.GetOrganSubset(organIds));
//        return ResultUtil.success(concurrentHashMaps);
//    }
}
