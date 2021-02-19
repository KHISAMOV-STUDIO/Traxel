package ru.traxel.traxel.springClasses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"ru.traxel.traxel"})
public class TraxelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraxelApplication.class, args);
	}

}
