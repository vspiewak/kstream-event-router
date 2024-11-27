package com.vspiewak.kstream_events_router.streams;

import com.fasterxml.jackson.databind.JsonNode;
import com.vspiewak.kstream_events_router.config.TopicProperties;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopologyDefinition {

    @Autowired
    private TopicProperties topicProperties;

    public Topology buildTopology(StreamsBuilder builder) {

        Serializer<JsonNode> jsonNodeSerializer = new JsonSerializer();
        Deserializer<JsonNode> jsonNodeDeserializer = new JsonDeserializer();
        Serde<JsonNode> jsonNodeSerde = Serdes.serdeFrom(jsonNodeSerializer, jsonNodeDeserializer);

        builder
                .stream(
                        topicProperties.inputTopic(),
                        Consumed.with(
                                        Serdes.String(),
                                        jsonNodeSerde
                                )
                                .withName("input-source")
                )
                .to(new CountryTopicExtractor(topicProperties));

        return builder.build();

    }

}
