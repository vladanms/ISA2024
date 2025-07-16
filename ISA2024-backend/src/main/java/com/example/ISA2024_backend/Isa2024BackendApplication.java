package com.example.ISA2024_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EntityScan(basePackages = "com.example.ISA2024_backend.model")
// @EnableJpaRepositories(basePackages = "com.example.ISA2024_backend.repository")
public class Isa2024BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Isa2024BackendApplication.class, args);
	}

}
