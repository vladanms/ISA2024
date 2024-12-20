package com.example.ISA2024_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"model"})
@ComponentScan(basePackages = {"dto"})
@ComponentScan(basePackages = {"controller"})
@ComponentScan(basePackages = {"service"})
@ComponentScan(basePackages = {"repository"})
@ComponentScan(basePackages = {"com.example.ISA2024_backend.config"})
public class Isa2024BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Isa2024BackendApplication.class, args);
	}

}
