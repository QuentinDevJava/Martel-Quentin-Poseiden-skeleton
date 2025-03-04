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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the application in test mode. Only active when the
 * 'test' or 'local' profile is used.
 * 
 * <ul>
 * <li>Allows access to certain pages without authentication.</li>
 * <li>Restricts access to certain pages to users with the "ADMIN" role.</li>
 * <li>Uses BCrypt password encoding.</li>
 * <li>Defines an in-memory user "user" and an admin "admin".</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
@Profile({ "test", "local" })
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

				.requestMatchers("/login", "/css/**", "/logout", "/error", "/h2/**").permitAll()

				.requestMatchers("/user/**", "/secure/**").hasRole("ADMIN")

				.anyRequest().authenticated())

				.formLogin(Customizer.withDefaults());

		return http.build();
	}

	/**
	 * Creates an in-memory user service for manual testing.
	 * 
	 * This method is used for authentication and authorization tests in development
	 * environments.
	 * 
	 * @return an {@link UserDetailsService} configured with two in-memory users: a
	 *         "user" and an "admin".
	 */
	@Bean
	UserDetailsService users(PasswordEncoder passwordEncoder) {

		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("user")).roles("USER")
				.build();
		UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("admin")).roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);

	}

}
