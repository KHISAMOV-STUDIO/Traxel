package ru.traxel.traxel.springClasses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.traxel.traxel"})
public class TraxelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraxelApplication.class, args);
	}

}
