package com.espe.gimnasio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendGimnasioV2Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendGimnasioV2Application.class, args);
	}

}
