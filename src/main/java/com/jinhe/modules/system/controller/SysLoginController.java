package com.jinhe.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.config.JwtConfig;

import com.jinhe.common.util.*;

import com.jinhe.modules.system.dto.SysLogin;
import com.jinhe.modules.system.dto.SysLoginDto;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.net.URLDecoder;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @ApiOperation(value = "登录获取token", notes = "登录获取token")
    @RequestMapping(value = "Login", method = RequestMethod.POST)
    @SysLog(value = "Login")
    public Result login(@RequestBody SysLogin login) throws InstantiationException, IllegalAccessException {
           String password="";

          password=EncryptUtil.getInstance().Base64Decode(login.getPassWord());

//        login.setPassWord(EncryptUtil.sgetInstance().MD5_32(login.getPassWord()));
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("NORMALIZED_USERNAME", login.getUserName());
//        userQueryWrapper.and(wrapper -> wrapper.lt("LOCK_OUT_TIME", new Date()).or().isNull("LOCK_OUT_TIME"));
        SysUser SysUser = iSysUserService.getBaseMapper().selectOne(userQueryWrapper);
        if (null == SysUser) {
            return ResultUtil.error(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        if(SysUser.getLockOutTime()!=null)
        {
            if(SysUser.getLockOutTime().getTime()>System.currentTimeMillis())
            {
                return ResultUtil.error(-1,"登录失败次数过多，请稍后重试");
            }
        }
        if(!SysUser.getPasswordHash().equals(password))
        {
            int accessFailedCount=0;
            if(SysUser.getAccessFailedCount()!=null)
            {
                accessFailedCount=SysUser.getAccessFailedCount();
            }
            accessFailedCount+=1;
            SysUser.setAccessFailedCount(accessFailedCount);
            if(accessFailedCount>5)
            {
                Date now = new Date();
                //增加5分钟
                Date afterDate = new Date(now .getTime() + 300000);
                SysUser.setLockOutTime(afterDate);
            }
            iSysUserService.updateById(SysUser);
            return ResultUtil.error(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }else{
            SysUser.setAccessFailedCount(0);
            SysUser.setLockOutTime(null);
            iSysUserService.updateById(SysUser);
        }

        SysLoginDto sysLogin = Mapper.ModelToModel(SysUser, SysLoginDto.class);
        if (sysLogin.getState() == 3 || sysLogin.getState() == 2) {
            return ResultUtil.error(ResultEnum.OBSOLETE);
        }
        String token = jwtConfig.createToken(sysLogin.getId());
        sysLogin.setToken(token);
        sysLogin.setTokenExpireTime(7200000);
        sysLogin.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        return ResultUtil.success(sysLogin);
    }

}
