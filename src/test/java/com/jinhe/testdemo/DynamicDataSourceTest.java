package com.jinhe.testdemo;



import com.jinhe.modules.sys.entity.SysLogEntity;
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
        SysLogEntity log1 = dataSourceTestService.queryLog(1L);
        System.out.println("----------------------------------------------------------");
        System.out.println(ToStringBuilder.reflectionToString(log1));
        System.out.println("----------------------------------------------------------");

        //数据源2
        SysLogEntity log2 = dataSourceTestService.queryLog2(1L);
        System.out.println("==========================================================");
        System.out.println(ToStringBuilder.reflectionToString(log2));
        System.out.println("==========================================================");
        //数据源1
        SysLogEntity log3 = dataSourceTestService.queryLog(1L);
        System.out.println(ToStringBuilder.reflectionToString(log3));
        System.out.println("----------------------------------------------------------");


        SysLogEntity log = new SysLogEntity();
        log.setId(1L);
        log.setCreateDate(new Date());
        log.setUsername("测试数据源事务");
        log.setTime(System.currentTimeMillis());

        boolean b1 = dataSourceTestService.insertLog1(log);
        System.out.println("---------------------------------------"+b1);
        boolean b2 = dataSourceTestService.insertLog2(log);
        System.out.println("---------------------------------------"+b2);
        boolean b3 = dataSourceTestService.insertLog3(log);
        System.out.println("---------------------------------------"+b3);

    }

}
