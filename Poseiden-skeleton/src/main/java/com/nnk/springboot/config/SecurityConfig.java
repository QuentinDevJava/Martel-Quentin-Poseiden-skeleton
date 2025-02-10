package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(auth -> {
			auth.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll();
			auth.requestMatchers("/**").hasAnyRole("USER", "ADMIN");
			auth.requestMatchers("/admin/**").hasRole("ADMIN");
			auth.requestMatchers("/css/**").permitAll();
			auth.anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults()).build();

	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() { // USER en mémoire pour tester spring security login
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER")
				.build();
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

}