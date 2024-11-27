package com.vspiewak.kstream_events_router.config;

import com.vspiewak.kstream_events_router.streams.TopologyDefinition;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class KafkaSteamsConfig {

    private static final Logger log = LoggerFactory.getLogger(KafkaSteamsConfig.class);

    @Bean("mainTopology")
    public Topology offerTopology(
            StreamsBuilder builder,
            TopologyDefinition topologyDefinition
    ) {

        Topology mainTopology = topologyDefinition.buildTopology(builder);
        log.info("Topology description :\n{}", mainTopology.describe());
        return mainTopology;

    }
}

