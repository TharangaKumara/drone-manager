package com.musalasoft.dronemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class DroneManagerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DroneManagerApplication.class);

	@PostConstruct
	public void postConstruct() {
		Runtime.getRuntime().addShutdownHook(new Thread(() ->
				LOGGER.debug("application shutdown {}", LocalDate.now())
		));
	}

	public static void main(String[] args) {
		SpringApplication.run(DroneManagerApplication.class, args);
	}


}
