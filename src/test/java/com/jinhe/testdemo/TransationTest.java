//package com.jinhe.testdemo;
//
//import com.jinhe.modules.system.entity.SysLog;
//import com.jinhe.service.TransationTestService;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TransationTest {
//    @Autowired
//    private TransationTestService transationTestService;
//
//    @Test
//    public void test(){
//        //数据源1
//        SysLog log1 = transationTestService.queryLog(1L);
//        System.out.println("----------------------------------------------------------");
//        System.out.println(ToStringBuilder.reflectionToString(log1));
//        System.out.println("----------------------------------------------------------");
//        SysLog log = new SysLog();
//
//        boolean b2 = transationTestService.insertLog2(log);
//        System.out.println("---------------------------------------"+b2);
//
//    }
//
//}
