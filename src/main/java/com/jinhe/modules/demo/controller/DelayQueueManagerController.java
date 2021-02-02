package com.jinhe.modules.demo.controller;

import com.jinhe.config.DelayQueueManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/demo/DelayQueueManager")
public class DelayQueueManagerController {

    @Autowired
    DelayQueueManager delayQueueManager;
 

    /**
     * 查询用户列表bg
     *
     * @return
     */
    @ApiOperation(value = "延迟队列测试", notes = "延迟队列测试")
    @RequestMapping(value = "DelayQueueTest", method = RequestMethod.GET)
    public void testLog(String param) {

    }

}
