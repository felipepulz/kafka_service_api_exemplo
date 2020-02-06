package br.com.kafka.api.infrastructure.kafka;

import br.com.kafka.api.application.events.KafkaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaEventListener {

    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;
    private final String topic;

    @Autowired
    public KafkaEventListener(KafkaTemplate<String, KafkaEvent> kafkaTemplate,
                              @Value("${app.kafka.topics.my-kafka}") String changeCustomerTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = changeCustomerTopic;
    }

    @EventListener(KafkaEvent.class)
    public void onRecommendationDone(KafkaEvent event) {
        kafkaTemplate.send(topic, event.hashCode() + "", event);
    }
}
