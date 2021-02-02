package com.jinhe.testdemo;


import com.jinhe.config.DelayQueueManager;
import com.jinhe.config.DelayTask;
import com.jinhe.domain.TaskBaseDomain;
import com.jinhe.modules.system.service.ISysLogService;
import com.jinhe.service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncThreadPoolTest {

    @Autowired
    AsyncService asyncService;


    @Test
    public void test1() throws InterruptedException {
        int count = 10;
        for (int i = 0; i < count; i++) {
            asyncService.doSomething("index = " + i);
        }
        sleep(100000);
    }

    @Test
    public void test2() throws IOException {


    }

    @Test
    public void integer() {

    }

    @Test
    public void dateTest() {

    }
}
