package br.com.kafka.api.application.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventBroadcast {

    private final ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    public KafkaEventBroadcast(ApplicationEventMulticaster applicationEventMulticaster) {
        this.applicationEventMulticaster = applicationEventMulticaster;
    }

    @Async
    public void fireEvent(KafkaEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }
}
