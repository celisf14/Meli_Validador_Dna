package com.meli.validarDna.Validadordna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.*"})
public class ValidadordnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidadordnaApplication.class, args);
	}

}
