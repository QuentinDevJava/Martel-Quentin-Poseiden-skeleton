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

				.requestMatchers("/login", "/css/**", "/logout").permitAll()

				.requestMatchers("/user/**").hasRole("ADMIN")

				.anyRequest().authenticated())

				.exceptionHandling(handling -> handling.accessDeniedPage("/error403"))

				.formLogin(Customizer.withDefaults());

		return http.build();
	}

	/**
	 * Defines the password encoder.
	 * 
	 * @return the BCrypt encoder.
	 */
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Creates an in-memory user details service. (for test in
	 * LoginControlleurTest.)
	 * 
	 * @return the UserDetailsService.
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
