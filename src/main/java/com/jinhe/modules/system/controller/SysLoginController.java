package com.jinhe.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.config.JwtConfig;
import com.jinhe.common.util.*;
import com.jinhe.modules.system.dto.SysLogin;
import com.jinhe.modules.system.dto.SysLoginDTO;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

/**
 * @author swf
 * @since 2020-05-15
 */
@RestController
@CrossOrigin
@RequestMapping("/allowApi/SysLogin")
@Api(description = "登录接口", tags = {"system-SysLogin"})
public class SysLoginController {
    @Resource
    private JwtConfig jwtConfig;
    @Autowired
    ISysUserService iSysUserService;
    private int minutes;

    @ApiOperation(value = "登录获取token", notes = "登录获取token")
    @RequestMapping(value = "Login", method = RequestMethod.POST)
    @SysLog(value = "Login")
    public Result login(@RequestBody SysLogin login) throws InstantiationException, IllegalAccessException {

        String password = EncryptUtil.getInstance().Base64Decode(login.getPassWord());
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("NORMALIZED_USERNAME", login.getUserName());
        SysUser SysUser = iSysUserService.getBaseMapper().selectOne(userQueryWrapper);
        if (null == SysUser) {
            return ResultUtil.error(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        if (SysUser.getLockOutTime() != null) {
            if (SysUser.getLockOutTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() > System.currentTimeMillis()) {
                return ResultUtil.error(-1, "登录失败次数过多，请稍后重试");
            }
        }
        if (!SysUser.getPasswordHash().equals(password)) {
            int accessFailedCount = 0;
            if (SysUser.getAccessFailedCount() != null) {
                accessFailedCount = SysUser.getAccessFailedCount();
            }
            ++accessFailedCount;
            SysUser.setAccessFailedCount(accessFailedCount);
            if (accessFailedCount > minutes) {
                SysUser.setLockOutTime(LocalDateTime.now().withMinute(minutes));
            }
            iSysUserService.updateById(SysUser);
            return ResultUtil.error(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        } else {
            SysUser.setAccessFailedCount(0);
            SysUser.setLockOutTime(null);
            iSysUserService.updateById(SysUser);
        }

        SysLoginDTO sysLogin = Mapper.ModelToModel(SysUser, SysLoginDTO.class);
        if (sysLogin.getState() == 3 || sysLogin.getState() == 2) {
            return ResultUtil.error(ResultEnum.OBSOLETE);
        }
        String token = jwtConfig.createToken(sysLogin.getId());
        sysLogin.setToken(token);
        sysLogin.setTokenExpireTime(7200000);
        sysLogin.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        return ResultUtil.success(sysLogin);
    }

    @ApiOperation(value = "手机app登录", notes = "手机app登录")
    @RequestMapping(value = "loginApp", method = RequestMethod.POST)
    @SysLog(value = "loginApp")
    public Result loginApp(@RequestBody SysLogin login) throws InstantiationException, IllegalAccessException {

        String password = EncryptUtil.getInstance().Base64Decode(login.getPassWord());
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("NORMALIZED_USERNAME", login.getUserName());
        SysUser SysUser = iSysUserService.getBaseMapper().selectOne(userQueryWrapper);
        if (null == SysUser) {
            return ResultUtil.error(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        if (SysUser.getLockOutTime() != null) {
            if (SysUser.getLockOutTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() > System.currentTimeMillis()) {
                return ResultUtil.error(-1, "登录失败次数过多，请稍后重试");
            }
        }
        if (!SysUser.getPasswordHash().equals(password)) {
            int accessFailedCount = 0;
            if (SysUser.getAccessFailedCount() != null) {
                accessFailedCount = SysUser.getAccessFailedCount();
            }
            accessFailedCount += 1;
            SysUser.setAccessFailedCount(accessFailedCount);
            if (accessFailedCount > 5) {
                Date now = new Date();
                //增加5分钟
                Date afterDate = new Date(now.getTime() + 300000);
                SysUser.setLockOutTime(LocalDateTime.now().withMinute(5));
            }
            iSysUserService.updateById(SysUser);
            return ResultUtil.error(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        } else {
            SysUser.setAccessFailedCount(0);
            SysUser.setLockOutTime(null);
            iSysUserService.updateById(SysUser);
        }

        SysLoginDTO sysLogin = Mapper.ModelToModel(SysUser, SysLoginDTO.class);
        if (sysLogin.getState() == 3 || sysLogin.getState() == 2) {
            return ResultUtil.error(ResultEnum.OBSOLETE);
        }
        String token = jwtConfig.createToken(sysLogin.getId(), 1000 * 30000);
        sysLogin.setToken(token);
        sysLogin.setTokenExpireTime(1000 * 30000);
        sysLogin.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        return ResultUtil.success(sysLogin);
    }
}
