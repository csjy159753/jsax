package com.jinhe.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.*;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.domian.UserInfo;
import com.jinhe.modules.system.dto.SysPasswordDTO;
import com.jinhe.modules.system.dto.SysUserDTO;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.entity.SysUserOrgan;
import com.jinhe.modules.system.service.ISysUserOrganService;
import com.jinhe.modules.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/system/sys-user")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysUserController {
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private UserInfo userInfo;
    @Autowired
    private ISysUserOrganService iSysUserOrganService;

    /**
     * 新增用户
     *
     * @return
     */
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    @SysLog(value = "测试注解日志切面新增用户saveUser")
    public Result saveUser(@RequestBody SysUserDTO sysUserDto) throws InstantiationException, IllegalAccessException {

        // 密码复杂度 10位4选3
        String passwordHash = sysUserDto.getPasswordHash();

        if (!userInfo.CheckPassword(passwordHash)) {
            return ResultUtil.error(ResultEnum.USER_UPDATE_PASSWORD_ERROR);
        }
        ResultEnum resultEnum = userInfo.NormalizedUsername(sysUserDto.getNormalizedUsername());
        if (resultEnum != ResultEnum.USER_NAME_CORRECT) {
            return ResultUtil.success(resultEnum);
        }

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("NORMALIZED_USERNAME", sysUserDto.getNormalizedUsername());
        SysUser sysUser = iSysUserService.getBaseMapper().selectOne(queryWrapper);
        if (sysUser != null) {
            return ResultUtil.error(ResultEnum.USER_NAME_ALREADY_EXISTS);
        }
        sysUserDto.setPasswordHash(EncryptUtil.getInstance().MD5_32(sysUserDto.getPasswordHash()));

        sysUserDto.setId(StringUtils.getGUID());
        sysUser = Mapper.ModelToModel(sysUserDto, SysUser.class);
        iSysUserService.save(sysUser);
        return ResultUtil.success();
    }


    /**
     * 重置密码
     *
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @RequestMapping(value = "ModifyByOrganRole", method = RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面重置密码updatePassword")
    public Result updatePassword(@RequestBody SysPasswordDTO from) {
        // 密码复杂度 10位4选3
        if (!userInfo.CheckPassword(from.getPassWordHash())) {
            return ResultUtil.error(ResultEnum.USER_UPDATE_PASSWORD_ERROR);
        }
        try {
            SysUser sysUser = iSysUserService.getById(from.getUserId());
            if (sysUser != null) {
                sysUser.setPasswordHash(EncryptUtil.getInstance().MD5_32(from.getPassWordHash()));
                UpdateWrapper<SysUser> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper.set("PASSWORD_HASH", sysUser.getPasswordHash());
                userUpdateWrapper.eq("ID", sysUser.getId());
                iSysUserService.update(userUpdateWrapper);
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }

    }


    /**
     * 禁用/恢复用户
     *
     * @return
     */
    @ApiOperation(value = "禁用/恢复用户", notes = "禁用/恢复用户")
    @RequestMapping(value = "updateUserState/{userId}/{state}", method = RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面禁用/禁用/恢复用户updateUserState")
    public Result updateUserState(@PathVariable String userId, @PathVariable Integer state) {
        try {
            SysUser sysUser = iSysUserService.getById(userId);
            if (sysUser != null) {
                sysUser.setState(state);
                iSysUserService.updateById(sysUser);
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("ableUserById", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
    }

    /**
     * 更新信息
     *
     * @return
     */
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @RequestMapping(value = "UpdateInfo", method = RequestMethod.PUT)
    @SysLog(value = "UpdateInfo")
    public Result UpdateInfo(@RequestBody SysUserDTO sysUserDto) throws InstantiationException, IllegalAccessException {
        if (sysUserDto.getOrganIds() == null || sysUserDto.getOrganIds().size() == 0) {
            //用户没有关联机构
            ResultUtil.error(ResultEnum.USER_NOT_RELEVANCY_ORGAN);
        }
        SysUser sysUser = null;
        sysUser = Mapper.ModelToModel(sysUserDto, SysUser.class);
        sysUser.setPasswordHash(null);
        iSysUserService.updateById(sysUser);
        //更新机构
        UpdateWrapper queryWrapper = new UpdateWrapper();
        queryWrapper.in("user_id", sysUserDto.getOrganIds());
        iSysUserOrganService.remove(queryWrapper);
        List<SysUserOrgan> organList = new ArrayList<>();
        List<String> organIds = sysUserDto.getOrganIds();
        for (String organId : organIds) {
            SysUserOrgan sysUserOrgan = new SysUserOrgan();
            sysUserOrgan.setOrganId(organId);
            sysUserOrgan.setUserId(sysUserDto.getId());
            organList.add(sysUserOrgan);
        }
        iSysUserOrganService.saveBatch(organList);
        return ResultUtil.success();
    }


    /**
     * 删除用户
     *
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "删除账户")
    @RequestMapping(value = "/removeById/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "测试注解日志切面删除账户removeById")
    public Result removeById(@PathVariable String id) {
        iSysUserOrganService.removeById(id);
        return ResultUtil.success();
    }

}
