package com.jinhe.modules.comm.controller;


import com.jinhe.common.dto.SysUserDto;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.vo.Result;
import com.jinhe.common.config.JwtConfig;
import com.jinhe.modules.comm.dto.Login;
import io.swagger.annotations.ApiOperation;


import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("Token")
public class TokenController {

    @Resource
    private JwtConfig jwtConfig;

    /**
     * 用户登录接口
     *
     * @param Login
     * @param
     * @return
     */
    @ApiOperation(value="登录获取token", notes="登录获取token")
    @PostMapping("/login")
    public Result login(@RequestBody Login Login) {
        JSONObject json = new JSONObject();
        /** 验证userName，passWord和数据库中是否一致，如不一致，直接return ResultUtil.errer(); 【这里省略该步骤】*/

        // 这里模拟通过用户名和密码，从数据库查询userId
        // 这里把userId转为String类型，实际开发中如果subject需要存userId，则可以JwtConfig的createToken方法的参数设置为Long类型
        String userId = 5 + "";
        String token = jwtConfig.createToken(userId);
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setToken(token);
        ResultUtil.success(sysUserDto);
        return ResultUtil.success(sysUserDto);
    }

    /**
     * 需要 Token 验证的接口
     */
    @PostMapping("/info")
    public Result<?> info() {
        return ResultUtil.success("info");
    }

    /**
     * 根据请求头的token获取userId
     *
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result<?> getUserInfo(HttpServletRequest request) {
        String usernameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return ResultUtil.success(usernameFromToken);
    }
    /*
        为什么项目重启后，带着之前的token还可以访问到需要info等需要token验证的接口？
        答案：只要不过期，会一直存在，类似于redis
     */

}