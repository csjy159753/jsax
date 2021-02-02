package com.jinhe.testdemo;


import com.jinhe.config.DelayQueueManager;
import com.jinhe.config.DelayTask;
import com.jinhe.domain.TaskBaseDomain;
import com.jinhe.modules.system.service.ISysLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayQueueTest {

    @Autowired
    DelayQueueManager delayQueueManager;
    @Autowired
    ISysLogService sysLogService;

    @Test
    public void test1() {

        delayQueueManager.put(new DelayTask(new TaskBaseDomain("aa", sysLogService), 1000));


        delayQueueManager.put(new DelayTask(new TaskBaseDomain("bb", sysLogService), 2000));

        delayQueueManager.put(new DelayTask(new TaskBaseDomain("cc", sysLogService), 3000));

        delayQueueManager.put(new DelayTask(new TaskBaseDomain("dd", sysLogService), 4000));

        delayQueueManager.put(new DelayTask(new TaskBaseDomain("ee", sysLogService), 5000));

        delayQueueManager.remove("ee");
        delayQueueManager.put(new DelayTask(new TaskBaseDomain("ff", sysLogService), 5000));

        try {
            sleep(90000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
