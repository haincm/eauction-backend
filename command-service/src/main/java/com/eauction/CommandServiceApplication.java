package com.eauction;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@Configuration
@OpenAPIDefinition(info = @Info(title = "E-Auction Command", version = "1.0", description = "E-Auction Command is used to create the product and Bid "))
@EnableEurekaClient
public class CommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandServiceApplication.class, args);
	}

	@Bean
	public NewTopic createTopic1() {
		return TopicBuilder.name("bidProduct").build();
	}
	@Bean
	public NewTopic createTopic2() {
		return TopicBuilder.name("bidProductUpdate").build();
	}

}
