package com.vspiewak.kstream_events_router.streams;

import com.fasterxml.jackson.databind.JsonNode;
import com.vspiewak.kstream_events_router.config.TopicProperties;
import org.apache.kafka.streams.processor.RecordContext;
import org.apache.kafka.streams.processor.TopicNameExtractor;

public class CountryTopicExtractor implements TopicNameExtractor<String, JsonNode> {

    private final TopicProperties topicProperties;

    public CountryTopicExtractor(TopicProperties topicProperties) {
        this.topicProperties = topicProperties;
    }

    @Override
    public String extract(String key, JsonNode value, RecordContext recordContext) {

        var country = value.get("country").asText();

        return switch (country) {
            case "france" -> topicProperties.franceTopic();
            case "spain" -> topicProperties.spainTopic();
            default -> topicProperties.otherTopic();
        };

    }
}
