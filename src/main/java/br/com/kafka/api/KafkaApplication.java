package br.com.kafka.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		System.setProperty("java.net.useSystemProxies", "true");
		SpringApplication.run(KafkaApplication.class, args);
	}

}
