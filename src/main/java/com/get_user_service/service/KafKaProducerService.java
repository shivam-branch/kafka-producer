package com.get_user_service.service;

import com.get_user_service.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafKaProducerService
{
    private static final Logger logger =
            LoggerFactory.getLogger(KafKaProducerService.class);

    //1. General topic with string payload

    @Value(value = "${general.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, UserDto> kafkaTemplate;


    public String sendUser(UserDto message)
    {
        ListenableFuture<SendResult<String, UserDto>> future
                = this.kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, UserDto>>() {
            @Override
            public void onSuccess(SendResult<String, UserDto> result) {
                logger.info("Sent message: " + message
                        + " with offset: " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message : " + message, ex);
            }
        });
        return "send";
    }
}

