package com.jinhe.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Administrator
 */
@Component
public class ScheduleDemo {
    @Scheduled(fixedDelay  = 5000)
    public void job() {
//        System.out.println("sadasdasdasdasdasdasdads");
    }
}

