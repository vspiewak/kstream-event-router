# kstream-events-router

Kafka Stream [Event Router](https://developer.confluent.io/patterns/event-processing/event-router) pattern implemented using [org.apache.kafka.streams.processor.TopicNameExtractor class](https://github.com/confluentinc/kafka/blob/master/streams/src/main/java/org/apache/kafka/streams/processor/TopicNameExtractor.java)

Read more here : https://vspiewak.com/routing-events-dynamically-using-kafka-streams

## Starting Services

    docker compose up -d


## Launching application

    ./mvnw clean spring-boot:run -Dspring-boot.run.profiles=init


## References
* https://developer.confluent.io/patterns/event-stream/event-stream
* https://developer.confluent.io/patterns/event-processing/event-router
