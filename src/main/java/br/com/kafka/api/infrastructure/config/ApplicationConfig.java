package br.com.kafka.api.infrastructure.config;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executor;

@Slf4j
@EnableCaching
@EnableAsync
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@EnableSwagger2
@Configuration
public class ApplicationConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(@Value("${spring.application.name}") String name) {
        return registry -> registry.config().commonTags("application", name);
    }

    @Bean
    ApplicationEventMulticaster applicationEventMulticaster(ThreadPoolTaskExecutor taskExecutor) {
        var eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(eventsTasksExecutor());
        return eventMulticaster;
    }

    private Executor eventsTasksExecutor() {
        log.info("creating Thread pool events");
        var threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("Events-");
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.initialize();
        log.info("Thread pool events created");
        return threadPoolTaskExecutor;
    }


}
