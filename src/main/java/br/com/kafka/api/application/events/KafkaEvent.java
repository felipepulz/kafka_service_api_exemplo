package br.com.kafka.api.application.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({"source", "timestamp"})
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaEvent extends ApplicationEvent {

    Object request;

    public KafkaEvent(String mensagem) {
        super(mensagem);
    }

    public void registerData(Object value) {
        request = value;
    }

}
