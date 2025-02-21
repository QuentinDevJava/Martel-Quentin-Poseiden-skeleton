package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application in production mode.
 * 
 * This class configures the security filter chain using
 * {@link SecurityFilterChain}, manages authentication with
 * {@link AuthenticationManager}, and provides a {@link BCryptPasswordEncoder}
 * for password encoding. It defines access rules for various resources and
 * integrates custom authentication logic.
 */
@Profile("prod")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/** The custom user details service for loading user details. */
	private final CustomUserDetailsService customUserDetailsService;

	SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	/**
	 * Configures HTTP security rules, including access permissions for specific
	 * URLs, and integrates form-based login and OAuth2 authentication.
	 *
	 * @param http The {@link HttpSecurity} object for configuring security rules.
	 * @return The configured {@link SecurityFilterChain}.
	 * @throws Exception If any error occurs during configuration.
	 */
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize

				.requestMatchers("/login", "/css/**", "/logout", "/error").permitAll()

				.requestMatchers("/user/**", "/secure/**").hasAnyRole("ADMIN")

				.anyRequest().authenticated()).exceptionHandling(handling -> handling.accessDeniedPage("/error403"))

				.formLogin(Customizer.withDefaults())

				.oauth2Login(Customizer.withDefaults());

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

	/**
	 * Configures an {@link AuthenticationManager} to handle user authentication.
	 * Uses the {@link CustomUserDetailsService} to load user details and the
	 * {@link BCryptPasswordEncoder} to verify passwords.
	 * 
	 * @param http                  The {@link HttpSecurity} object for configuring
	 *                              authentication.
	 * @param bCryptPasswordEncoder The {@link BCryptPasswordEncoder} for password
	 *                              encoding.
	 * @return The configured {@link AuthenticationManager}.
	 * @throws Exception If any error occurs during configuration.
	 */
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(customUserDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

}