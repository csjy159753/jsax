package com.jinhe.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerC {
    Logger logger = LoggerFactory.getLogger(getClass());
    @RabbitListener(queues = "topic.messages")
    //@RabbitListener(queues = "cord", containerFactory="myFactory")
    public void processMessage(String msg) {
        System.out.format("Receiving Messages: -----[%s]----- \n.", msg);
        logger.info("Receiving messages: -----[%s]----- \n.", msg);
    }
}
