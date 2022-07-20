package com.eauctionquery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "E-Auction Query", version = "1.0", description = "E-Auction Query is used to read the product and Bid information from database "))
@EnableEurekaClient
public class EauctionQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EauctionQueryApplication.class, args);
	}

}
