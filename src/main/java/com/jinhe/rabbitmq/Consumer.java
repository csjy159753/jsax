package com.jinhe.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    Logger logger = LoggerFactory.getLogger(getClass());
    @RabbitListener(queues = "topic.message")
    //@RabbitListener(queues = "cord", containerFactory="myFactory")
    public void processMessage(String msg) {
        System.out.format("Receiving Message: -----[%s]----- \n.", msg);
        logger.info(String.format("Receiving Message: -----[%s]----- \n.", msg));
    }
}
