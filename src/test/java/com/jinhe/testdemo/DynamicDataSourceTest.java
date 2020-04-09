package com.jinhe.testdemo;

import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.service.DataSourceTestService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DataSourceTestService dataSourceTestService;

    @Test
    public void test(){
        //数据源1
        SysLog log1 = dataSourceTestService.queryLog(1L);
        System.out.println("----------------------------------------------------------");
        System.out.println(ToStringBuilder.reflectionToString(log1));
        System.out.println("----------------------------------------------------------");

        //数据源2
        SysLog log2 = dataSourceTestService.queryLog2(1L);
        System.out.println("==========================================================");
        System.out.println(ToStringBuilder.reflectionToString(log2));
        System.out.println("==========================================================");
        //数据源1
        SysLog log3 = dataSourceTestService.queryLog(1L);
        System.out.println(ToStringBuilder.reflectionToString(log3));
        System.out.println("----------------------------------------------------------");


        SysLog log = new SysLog();
        log.setApplication("测试");
        log.setLevel("2");
        log.setUserName("ces");

        boolean b1 = dataSourceTestService.insertLog1(log);
        System.out.println("---------------------------------------"+b1);
        boolean b2 = dataSourceTestService.insertLog2(log);
        System.out.println("---------------------------------------"+b2);
        log.setId("11");
        boolean b3 = dataSourceTestService.insertLog3(log);
        System.out.println("---------------------------------------"+b3);

    }

}
