package com.orbitaltech.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrbitaltechApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrbitaltechApplication.class, args);
	}

}
