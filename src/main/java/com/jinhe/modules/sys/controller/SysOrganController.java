package com.jinhe.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.config.LongSwingConstants;
import com.jinhe.common.util.*;
import com.jinhe.config.SystemResultEnum;
import com.jinhe.modules.sys.dto.SysOrganAddDTO;
import com.jinhe.modules.sys.dto.SysOrganRoleDTO;
import com.jinhe.modules.sys.service.ISysOrganRoleService;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysOrganRole;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.sys.service.ISysOrganService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Autowired
    private ISysOrganRoleService iSysOrganRoleService;

    /**
     * 新增机构
     **/
    @ApiOperation(value = "新增机构", notes = "新增机构")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public Result saveOrUpdate(@RequestBody SysOrganAddDTO sysOrganAddDTO) {
        if (sysOrganAddDTO.getId() == null && sysOrganAddDTO.getType() == null) {
            sysOrganAddDTO.setType(LongSwingConstants.Number.ZERO);
        }
        if (sysOrganAddDTO == null) {
            return ResultUtil.error();
        }

        if (sysOrganAddDTO.getType() != null && !sysOrganAddDTO.getType().equals(LongSwingConstants.Number.ONE)
                && !sysOrganAddDTO.getType().equals(LongSwingConstants.Number.ZERO)) {
            return ResultUtil.error(SystemResultEnum.ORGAN_TYPE_ERROR);
        }
        SysOrgan sysOrgan = new SysOrgan();
        sysOrgan = EntityUtil.INSTANCE.copyValOnlyDestEmpty(sysOrgan, sysOrganAddDTO);
        if (!StringUtils.isEmpty(sysOrgan.getTag())) {
            QueryWrapper<SysOrgan> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysOrgan::getType, sysOrgan.getType());
            int count = iSysOrganService.count(queryWrapper);
            if (count > 0) {
                return ResultUtil.error(SystemResultEnum.ORGAN_TAG_REPEAT);
            }
        }

        /**
         * 删除之前的机构角色关联
         */
        UpdateWrapper<SysOrganRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(SysOrganRole::getOrganId, sysOrgan.getId());
        iSysOrganRoleService.remove(updateWrapper);
        iSysOrganService.saveOrUpdate(sysOrgan);
        if (sysOrgan.getParentId() != null) {
            iSysOrganService.saveOrUpdateChildrenNumAndLevel(sysOrgan.getParentId());
        } else if (sysOrgan.getParentId() == null) {
            iSysOrganService.saveOrUpdateChildrenNumAndLevel(sysOrgan.getId());
        }
        List<SysOrganRole> l = new ArrayList<>();
        SysOrgan finalSysOrgan = sysOrgan;
        if (sysOrganAddDTO.getType() != null && sysOrganAddDTO.getType() != 0) {
            sysOrganAddDTO.getListRoles().forEach(d -> {
                SysOrganRole sysOrganRole = new SysOrganRole();
                sysOrganRole.setOrganId(finalSysOrgan.getId());
                sysOrganRole.setRoleId(d);
                l.add(sysOrganRole);
            });
        }
        iSysOrganRoleService.saveBatch(l);
        return ResultUtil.success();
    }

    /**
     * 根据机构id查询下级组织机构 树形结构分级查询
     **/
    @ApiOperation(value = "根据机构id查询下级组织机构 树形结构分级查询", notes = "根据机构id查询下级组织机构 树形结构分级查询")
    @RequestMapping(value = "selectOrganByOrganId/{userId}", method = RequestMethod.GET)
    public Result<ListSub<SysOrganRoleDTO>> selectOrganByOrganId(@PathVariable String userId, Integer type, String organId, PageFilter pageFilter) {

        SysUser sysUser = iSysUserService.getById(userId);
        if (StringUtils.isEmpty(organId) && sysUser.getType() != null && !sysUser.getType().equals(LongSwingConstants.USER_TYPE_ADMIN)) {
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
        Page page = new Page(pageFilter.getStart(), pageFilter.getLength());
        IPage<SysOrganRoleDTO> iPage = iSysOrganRoleService.selectOrganByOrganId(page, type, organId);
        return ResultUtil.success(iPage);
    }


    @ApiOperation(value = "根据ID删除机构", notes = "根据ID删除机构")
    @RequestMapping(value = "remove/{userId}/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id, @PathVariable String userId) {
        SysUser sysUser = iSysUserService.getById(userId);
        if (sysUser == null || !LongSwingConstants.USER_TYPE_ADMIN.equals(sysUser.getType())) {
            return ResultUtil.error(SystemResultEnum.RESOURCE_PERMISSION_DENIED);
        }
        SysOrgan sysOrgan = iSysOrganService.getById(id);
        if (sysOrgan == null) {
            return ResultUtil.error(SystemResultEnum.ORGAN_NOT_FOUND);
        }
        QueryWrapper<SysOrgan> sysOrganQueryWrapper = new QueryWrapper<>();
        sysOrganQueryWrapper.lambda().eq(SysOrgan::getParentId, id);
        if (iSysOrganService.count(sysOrganQueryWrapper) > 0) {
            return ResultUtil.error(SystemResultEnum.ORGAN_EXIST_SUBSET_UNABLE_DEL);
        }
        iSysOrganService.removeById(id);
        iSysOrganService.saveOrUpdateChildrenNumAndLevel(sysOrgan.getParentId());
        return ResultUtil.success();
    }
}
