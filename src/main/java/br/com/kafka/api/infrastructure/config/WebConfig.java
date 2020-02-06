package br.com.kafka.api.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Configuration
public class WebConfig {

    private final GenericConversionService genericConversionService;

    @Autowired
    public WebConfig(GenericConversionService genericConversionService) {
        this.genericConversionService = genericConversionService;
    }

    @PostConstruct
    void registerConverters() {
        //genericConversionService.addConverter(String.class, RecommendationType.class, new RecommendationTypeConverter());
    }

    @Bean
    ObjectMapper createObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;

    }

    @Bean
    FilterRegistrationBean corsFilter() {
        final var cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedHeader(ALL);
        cors.addAllowedMethod(HttpMethod.POST);
        cors.addAllowedMethod(HttpMethod.GET);
        cors.addAllowedMethod(HttpMethod.OPTIONS);
        cors.addAllowedOrigin(ALL);

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);

        final var bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

    @Bean
    HttpMessageConverter<Object> textHtmlMessageConverter(){
        var messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(createObjectMapper());
        messageConverter.setSupportedMediaTypes(List.of(MediaType.TEXT_HTML));
        return messageConverter;
    }

}
