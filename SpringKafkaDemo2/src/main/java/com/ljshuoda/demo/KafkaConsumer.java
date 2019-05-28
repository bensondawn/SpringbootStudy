package com.ljshuoda.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "test")
    public void listen(ConsumerRecord<?, ?> record) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();
            logger.info("Receive： +++++++++++++++ Message:" + message);
        }
    }
}
