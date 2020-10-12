package com.jinhe.testdemo;

import com.jinhe.common.util.Mapper;
import com.jinhe.common.util.RedisUtil;
import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.modules.system.service.ISysLog2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLog2Service iSysLog2Service;
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testRedis() throws InstantiationException, IllegalAccessException {
        SysLog sysLog = new SysLog();
        sysLog.setId("111");
        sysLog.setCallSite("111");
        List<SysLog> list = new ArrayList<>();
        list.add(sysLog);
        redisUtil.set("testKey", list);
        logger.debug("这是debug日志...111");
        List<SysLog> st = (List<SysLog>) redisUtil.get("testKey");
        logger.debug("这是debug日志...");


    }

    @Test
    public void testRedis2() throws InstantiationException, IllegalAccessException {
        SysLog sysLog = new SysLog();
        sysLog.setId("111");
        sysLog.setCallSite("111");
        List<SysLog> list = new ArrayList<>();
        list.add(sysLog);
        redisUtil.set("testKey", list);
        logger.debug("这是debug日志...111");
        List<SysLog> st = (List<SysLog>) redisUtil.get("testKey");
        logger.debug("这是debug日志...");
        List<SysLog> gh= iSysLog2Service.listAll();
        logger.debug("这是debug日志...");

    }
}
