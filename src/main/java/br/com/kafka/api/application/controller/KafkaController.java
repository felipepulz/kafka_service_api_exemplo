package br.com.kafka.api.application.controller;

import br.com.kafka.api.application.events.KafkaEvent;
import br.com.kafka.api.application.events.KafkaEventBroadcast;
import br.com.kafka.api.application.request.KafkaRequest;
import br.com.kafka.api.application.response.KafkaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class KafkaController implements KafkaAPI {

    private final KafkaEventBroadcast kafkaEventBroadcast;

    @Autowired
    public KafkaController(KafkaEventBroadcast kafkaEventBroadcast) {
        this.kafkaEventBroadcast = kafkaEventBroadcast;
    }

    @Override
    public KafkaResponse sendData(KafkaRequest request) {
        var event = new KafkaEvent(request.getData());
        String date = CurrentTimeAndDate();
        request.setRegister(date);
        event.registerData(request);
        kafkaEventBroadcast.fireEvent(event);
        return new KafkaResponse("Veja o console do consumidor!!!!");
    }

    private String CurrentTimeAndDate() {
        DateFormat formatandoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatandoData.format(new Date(System.currentTimeMillis()));
    }

}
