package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
@Profile("test")
public class TestSecurityConfig {

	/**
	 * Web.
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize

				.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()

				.requestMatchers("/", "/login", "/css/**", "/logout").permitAll()

				.requestMatchers("/user/**").hasRole("ADMIN")

				.anyRequest().authenticated())

				.formLogin(Customizer.withDefaults())

				.oauth2Login(Customizer.withDefaults());

		return http.build();
	}

	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * User details service.
	 *
	 * @return the user details service
	 */
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("userTest").password(passwordEncoder().encode("userTest"))
				.roles("USER").build();
		UserDetails admin = User.builder().username("adminTest").password(passwordEncoder().encode("adminTest"))
				.roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

}