package com.example.Demo.Bot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		servers = @Server(
				url = "http://147.45.104.116:8011"
		)
)
public class DemoBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBotApplication.class, args);
	}

}
