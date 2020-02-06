package br.com.kafka.api.application.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class KafkaRequest {

    @NotBlank(message = "data must be not null")
    String data;

    String register;

}
