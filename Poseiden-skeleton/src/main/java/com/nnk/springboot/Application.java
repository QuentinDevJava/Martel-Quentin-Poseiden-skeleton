package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The main entry point for the spring-boot-skeleton application.
 * 
 * This class initializes the Spring Boot application by running the
 * {@link SpringApplication}.
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Declares a password encoder for hashing passwords using the BCrypt algorithm.
	 *
	 * @return A {@link BCryptPasswordEncoder} for password encoding.
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
