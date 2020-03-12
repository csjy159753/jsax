package com.jinhe.modules.sys.controller;


import com.jinhe.common.annotation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统日志
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

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
