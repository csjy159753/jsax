package com.jinhe.modules.system.controller;

import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.config.JwtConfig;

import com.jinhe.common.util.EncryptUtil;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;

import com.jinhe.modules.system.dto.SysLogin;
import com.jinhe.modules.system.dto.SysLoginDto;
import com.jinhe.modules.system.service.ISysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author swf
 * @since 2020-05-15
 */
@RestController
@CrossOrigin
@RequestMapping("/allowApi/SysLogin")
@Api(description = "登录接口",tags = {"system-SysLogin"})
public class SysLoginController {

    @Resource
    private JwtConfig jwtConfig;

    @Autowired
    ISysLoginService iSysLoginService;


    @ApiOperation(value="登录获取token", notes="登录获取token")
    @RequestMapping(value = "Login",method = RequestMethod.POST)
    @SysLog(value = "Login")
    public Result login(@RequestBody SysLogin login) {

        login.setPassWord(EncryptUtil.getInstance().MD5_32(login.getPassWord()));
        SysLoginDto sysLogin = iSysLoginService.selectUserByLogin(login);

        if(null == sysLogin){
            return ResultUtil.error(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }

        if(sysLogin.getState() == 3 || sysLogin.getState() == 2){
            return ResultUtil.error(ResultEnum.OBSOLETE);
        }
        String token = jwtConfig.createToken(sysLogin.getId());

        sysLogin.setToken(token);
        sysLogin.setTokenExpireTime(7200000);
        sysLogin.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        return ResultUtil.success(sysLogin);
    }

}
