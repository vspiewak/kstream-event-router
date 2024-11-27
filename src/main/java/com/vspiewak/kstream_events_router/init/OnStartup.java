package com.vspiewak.kstream_events_router.init;

import com.vspiewak.kstream_events_router.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("init")
@Component
public class OnStartup {

    @Value("${spring.kafka.topics.inputTopic}")
    String topicName;

    @Autowired
    private KafkaProducer kafkaProducer;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {

        payloads.forEach(payload -> {
            kafkaProducer.sendMessage(topicName, payload[0], payload[1]);
        });

    }

    public static final List<String[]> payloads = List.of(
            new String[]{"poison pill", "poison pill"},
            new String[]{
                    "event-id-1",
                    """
                    {
                        "country": "france",
                        "message": "Bonjour ! 🇫🇷"
                    }
                    """
            },
            new String[]{
                    "event-id-2",
                    """
                    {
                        "country": "spain",
                        "message": "¡Buen día! 🇪🇸"
                    }
                    """
            },
            new String[]{
                    "event-id-3",
                    """
                    {
                        "country": "japan",
                        "message": "こんにちは 🇯🇵"
                    }
                    """
            }
    );
}
