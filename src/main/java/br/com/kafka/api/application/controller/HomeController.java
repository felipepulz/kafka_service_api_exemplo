package br.com.kafka.api.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.ApiInfo;

import java.util.Map;

@ApiIgnore
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class HomeController {

    private final ApiInfo apiInfo;

    @Autowired
    public HomeController(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    @GetMapping
    public Map<String,String> index() {

        return Map.of("Name", apiInfo.getTitle(), "Version", apiInfo.getVersion());
    }

}
