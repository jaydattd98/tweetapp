package com.tweetapp.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    private static final String TOPIC_NAME = "tweetapp";
    private static final String GROUP_ID = "group_id";

//    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
//    public void consume(String message) {
//        log.info(String.format("Message received -> %s", message));
//    }
}
