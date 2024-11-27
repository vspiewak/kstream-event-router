package com.vspiewak.kstream_events_router.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String key, String message) {

        kafkaTemplate
                .send(topicName, key, message)
                .whenComplete((_, ex) -> {
                    if (ex == null) {
                        log.info("message sent to topic: {}", message);
                    } else {
                        log.error("error sending message: {}", ex.getMessage());
                    }
                });

    }

}
