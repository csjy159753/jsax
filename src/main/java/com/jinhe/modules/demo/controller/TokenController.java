package com.jinhe.modules.demo.controller;


import com.jinhe.common.config.JwtConfig;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.demo.dto.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@RestController
@CrossOrigin
@Api(tags = {"demo"})
@RequestMapping("/demo/Token")
public class TokenController {

    @Resource
    private JwtConfig jwtConfig;



    /**
     * 需要 Token 验证的接口
     */
    @PostMapping("/info")
    public Result info() {
        return ResultUtil.success("info");
    }

    /**
     * 根据请求头的token获取userId
     *
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        String usernameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return ResultUtil.success(usernameFromToken);
    }
    /*
        为什么项目重启后，带着之前的token还可以访问到需要info等需要token验证的接口？
        答案：只要不过期，会一直存在，类似于redis
     */

}