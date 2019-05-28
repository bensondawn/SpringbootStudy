package com.ljshuoda.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

//    private final KafkaTemplate<String,String> kafkaTemplate;
//
//    @Autowired
//    public KafkaProducer(KafkaTemplate<String,String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void send(String msg) {
        logger.info("Send： **************** Message:" + msg);
        //发送消息
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test", msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("Produce: The message failed to be sent:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringObjectSendResult) {
                //TODO 业务处理
                logger.info("Produce: The message was sent successfully:");
            }
        });
    }
}
