package com.vspiewak.kstream_events_router.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.topics")
public record TopicProperties(
        String inputTopic,
        String franceTopic,
        String spainTopic,
        String otherTopic
) {
}
