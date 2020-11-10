package com.jinhe.modules.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobService {
    //cron = "0/3 40 11 * * ?" 每天11:40触发，没三秒执行一次
    @Scheduled(cron = "0/3 * * * * ?")
    public void updateRecords() {
       Date date=new Date();
    }
}
