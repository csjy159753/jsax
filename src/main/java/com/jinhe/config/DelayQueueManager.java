package com.jinhe.config;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;
/**
 * @author rls
 */
@Component
public class DelayQueueManager implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(DelayQueueManager.class);
    private DelayQueue<DelayTask> delayQueue = new DelayQueue<>();

    /**
     * 加入到延时队列中
     * @param task
     */
    public void put(DelayTask task) {
        logger.info("加入延时任务：{}", task);
        delayQueue.put(task);
    }

    /**
     * 取消延时任务
     * @param task
     * @return
     */
    public boolean remove(DelayTask task) {
        logger.info("取消延时任务：{}", task);
        return delayQueue.remove(task);
    }

    /**
     * 取消延时任务
     * @param taskid
     * @return
     */
    public boolean remove(String taskid) {
        return remove(new DelayTask(new TaskBase(taskid), 0));
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("初始化延时队列");
        Executors.newSingleThreadExecutor().execute(new Thread(this::excuteThread));
    }

    /**
     * 延时任务执行线程
     */
    private void excuteThread() {
        while (true) {
            try {
                DelayTask task = delayQueue.take();
                processTask(task);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * 内部执行延时任务
     * @param task
     */
    private void processTask(DelayTask task) {
        logger.info("执行延时任务：{}", task);
        //根据task中的data自定义数据来处理相关逻辑，例 if (task.getData() instanceof XXX) {}
    }
}