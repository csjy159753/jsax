package com.jinhe.api;

import com.jinhe.common.annotation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 * 系统日志
 *
 * @author faymanwang
 * @email 1057438332@gmail.com
 * @date 2019-08-18 20:00:36
 */
@RestController
@RequestMapping("log")
public class LogController {


    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="测试日志方法", notes="测试日志方法")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面")
    public String testLog (String param,int num){
        return param+num;
    }

}
