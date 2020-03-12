package com.jinhe.testdemo;



import com.jinhe.modules.sys.entity.SysLogEntity;
import com.jinhe.service.TransationTestService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TransationTest {
    @Autowired
    private TransationTestService transationTestService;

    @Test
    public void test(){
        //数据源1
        SysLogEntity log1 = transationTestService.queryLog(1L);
        System.out.println("----------------------------------------------------------");
        System.out.println(ToStringBuilder.reflectionToString(log1));
        System.out.println("----------------------------------------------------------");



        SysLogEntity log = new SysLogEntity();
        log.setId(2L);
        log.setCreateDate(new Date());
        log.setUsername("测试数据源事务");
        log.setTime(System.currentTimeMillis());

//        boolean b1 = transationTestService.insertLog(log);
//        System.out.println("---------------------------------------"+b1);

        boolean b2 = transationTestService.insertLog2(log);
        System.out.println("---------------------------------------"+b2);

    }

}
