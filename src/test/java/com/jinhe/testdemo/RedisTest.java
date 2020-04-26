package com.jinhe.testdemo;

import com.jinhe.common.util.Mapper;
import com.jinhe.common.util.RedisUtil;
import com.jinhe.modules.system.dto.SysLogVo;
import com.jinhe.modules.system.entity.SysLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void testRedis() throws InstantiationException, IllegalAccessException {
//        SysLog  sysLog=new SysLog();
//        sysLog.setId("111");
//        sysLog.setCallSite("111");
//        List<SysLog> list=new ArrayList<>();
//        list.add(sysLog);
//        redisUtil.set("testKey",list);
//        List<SysLog> st= (List<SysLog>)redisUtil.get("testKey");
//        logger.debug("这是debug日志...");
        //添加excel的读取和解析
//        ExcelReader<SysLog> excelReader=new ExcelReader<>();
//        List<SysLog> li=excelReader.readExcel("C:\\Users\\Administrator\\Desktop\\aaa.xlsx", new ParseExcelRow<SysLog>() {
//            @Override
//            public SysLog execute(Row row) {
//
//                return null;
//            }
//        });
        SysLog sysLog=new SysLog();
        sysLog.setId("111");
//        sysLog.setCreateTime(LocalDateTime.now());
        SysLogVo SS=  Mapper.MapToModel(sysLog, SysLogVo.class);

        logger.debug("这是debug日志...",SS);
    }

}
