package br.com.kafka.api.application.controller;

import br.com.kafka.api.application.request.KafkaRequest;
import br.com.kafka.api.application.response.KafkaResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Api
@Validated
@RequestMapping(path = "/kafka", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface KafkaAPI {

    @PostMapping(path = "/sendData")
    KafkaResponse sendData(@Valid @RequestBody KafkaRequest request);

}
	

