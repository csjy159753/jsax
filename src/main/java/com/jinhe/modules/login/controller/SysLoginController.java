package com.jinhe.modules.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.config.JwtConfig;
import com.jinhe.common.config.SystemType;
import com.jinhe.common.util.*;
import com.jinhe.common.config.SystemResultEnum;
import com.jinhe.modules.login.dto.SysLogin;
import com.jinhe.modules.login.dto.SysLoginDTO;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysLoginCountService;
import com.jinhe.modules.system.service.ISysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author swf
 * @since 2020-05-15
 */
@RestController
@CrossOrigin
@RequestMapping("/login/sysLogin")
@Api(tags = {"login"})
public class SysLoginController {

    @Autowired(required = false)
    HttpServletRequest request;
    @Resource
    private JwtConfig jwtConfig;
    @Autowired
    ISysUserService iSysUserService;
    private int minutes;
    @Autowired
    private ISysLoginLogService iSysLoginLogService;
    @Autowired
    private ISysLoginCountService iSysLoginCountService;

    @ApiOperation(value = "登录获取token", notes = "登录获取token")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @SysLog(value = "login")
    public Result<SysLoginDTO> login(@RequestBody SysLogin login) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        String password = EncryptUtil.getInstance().Base64Decode(login.getPassWord());
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("NORMALIZED_USERNAME", login.getUserName());
        SysUser SysUser = iSysUserService.getBaseMapper().selectOne(userQueryWrapper);
        if (null == SysUser) {
            return ResultUtil.error(SystemResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
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
            return ResultUtil.error(SystemResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        } else {
            SysUser.setAccessFailedCount(0);
            SysUser.setLockOutTime(null);
            iSysUserService.updateById(SysUser);
        }

        SysLoginDTO sysLogin = Mapper.ModelToModel(SysUser, SysLoginDTO.class);
        if (sysLogin.getState() == 3) {
            return ResultUtil.error(SystemResultEnum.USER_OBSOLETE);
        }
        if (sysLogin.getState() == 2) {
            return ResultUtil.error(SystemResultEnum.USER_AUDIT);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put(SystemType.TYPE, SystemType.LoginType.USER);
        map.put(SystemType.USER_ID, SysUser.getId());
        map.put(SystemType.USER_NAME, SysUser.getNormalizedUsername());
        String token = jwtConfig.createToken(SysUser.getId(), map);
        sysLogin.setToken(token);
        sysLogin.setTokenExpireTime(7200000);
        sysLogin.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));

        /**
         * 添加日志记录
         */
        String clientIp = HttpServletUtil.getIPAddress(request);
        iSysLoginLogService.saveInfo(SysUser.getId(), SysUser.getNormalizedUsername(), 2, clientIp);
        iSysLoginCountService.saveInfo(SysUser.getId(), SysUser.getNormalizedUsername(), 2);
        return ResultUtil.success(sysLogin);
    }

    @ApiOperation(value = "手机app登录", notes = "手机app登录")
    @RequestMapping(value = "loginApp", method = RequestMethod.POST)
    @SysLog(value = "loginApp")
    public Result<SysLoginDTO> loginApp(@RequestBody SysLogin login) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        String password = EncryptUtil.getInstance().Base64Decode(login.getPassWord());
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("NORMALIZED_USERNAME", login.getUserName());
        SysUser SysUser = iSysUserService.getBaseMapper().selectOne(userQueryWrapper);
        if (null == SysUser) {
            return ResultUtil.error(SystemResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
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
            return ResultUtil.error(SystemResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        } else {
            SysUser.setAccessFailedCount(0);
            SysUser.setLockOutTime(null);
            iSysUserService.updateById(SysUser);
        }

        SysLoginDTO sysLogin = Mapper.ModelToModel(SysUser, SysLoginDTO.class);
        if (sysLogin.getState() == 3) {
            return ResultUtil.error(SystemResultEnum.USER_OBSOLETE);
        }
        if (sysLogin.getState() == 2) {
            return ResultUtil.error(SystemResultEnum.USER_AUDIT);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put(SystemType.TYPE, SystemType.LoginType.USER);
        map.put(SystemType.USER_ID, SysUser.getId());
        map.put(SystemType.USER_NAME, SysUser.getNormalizedUsername());
        String token = jwtConfig.createToken(sysLogin.getId(), map, 100000 * 30000);
        sysLogin.setToken(token);
        sysLogin.setTokenExpireTime(1000 * 30000);
        sysLogin.setRefreshToken(StringUtils.getGUID());
        /**
         * 添加日志记录
         */
        /**
         * 添加日志记录
         */
        String clientIp = HttpServletUtil.getIPAddress(request);
        iSysLoginLogService.saveInfo(SysUser.getId(), SysUser.getNormalizedUsername(), 1, clientIp);
        iSysLoginCountService.saveInfo(SysUser.getId(), SysUser.getNormalizedUsername(), 1);
        return ResultUtil.success(sysLogin);
    }
}
