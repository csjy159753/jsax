package com.jinhe.service;

import com.jinhe.modules.system.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    @Autowired
    ISysLogService sysLogService;
    Logger log = LoggerFactory.getLogger(getClass());

    // 指定使用beanname为doSomethingExecutor的线程池
    @Async(value = "doSomethingExecutor")
    public String doSomething(String message) {
        log.info("do something, message={}", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("do something error: ", e);
        }
        return message;
    }
}
