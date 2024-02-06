package com.jeromerichard.pdfstream;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PdfstreamApplication {
	public static void main(String[] args) {
		SpringApplication.run(PdfstreamApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		// Instanciation de ModelMapper pour le projet et son utilisation
		return new ModelMapper();
	}
}
