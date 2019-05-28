package com.ljshuoda.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info("Send： **************** Message:" + message);
        kafkaTemplate.send("test", message);
    }
}
