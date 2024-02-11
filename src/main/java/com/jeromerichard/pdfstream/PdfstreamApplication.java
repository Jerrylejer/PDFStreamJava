package com.jeromerichard.pdfstream;

import com.jeromerichard.pdfstream.Config.WebConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@Import(WebConfig.class)
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
