package com.jinhe.modules.demo.controller;

import com.jinhe.common.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统日志
 *
 * @author
 * @email
 * @date 2019-08-18 20:00:36
 */
@RestController
@CrossOrigin
@Api(tags = {"demo"})
@RequestMapping("/demo/Log")
public class LogController {
    /**
     * 查询用户列表bg
     * @return
     */
    @ApiOperation(value="测试日志", notes="测试日志")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testLog (String param,int num){
        return param+num;
    }

}
