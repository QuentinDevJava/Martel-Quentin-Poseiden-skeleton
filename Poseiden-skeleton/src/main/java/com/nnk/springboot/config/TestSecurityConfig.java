package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the application in test mode. Only active when the
 * 'test' profile is used.
 * 
 * - Allows access to certain pages (login, logout, css) without authentication.
 * - Restricts access to "/user/**" pages to users with the "ADMIN" role. - Uses
 * BCrypt password encoding. - Defines an in-memory user "userTest" and an admin
 * "adminTest".
 */
@Configuration
@EnableWebSecurity
@Profile("test")
public class TestSecurityConfig {

	/**
	 * Configures HTTP security for the test mode.
	 * 
	 * @param http the HttpSecurity object for configuring HTTP security.
	 * @return the security filter chain.
	 * @throws Exception if there is a configuration error.
	 */
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize

				.requestMatchers("/login", "/css/**", "/logout", "/error", "/h2-console").permitAll()

				.requestMatchers("/user/**").hasRole("ADMIN")

				.anyRequest().permitAll())

				.formLogin(Customizer.withDefaults());

		return http.build();
	}

	/**
	 * Declares a password encoder for hashing passwords using the BCrypt algorithm.
	 * 
	 * @return A {@link BCryptPasswordEncoder} for password encoding.
	 */
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
