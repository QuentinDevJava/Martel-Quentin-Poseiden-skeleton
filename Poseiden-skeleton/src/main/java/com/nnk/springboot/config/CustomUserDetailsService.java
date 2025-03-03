package com.nnk.springboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

/**
 * Custom service to load user details in production. Implements Spring
 * Security's UserDetailsService.
 */
@Profile({ "prod" })
@Service
public class CustomUserDetailsService implements UserDetailsService {

	/** Service to access users. */
	private final UserService userService;

	CustomUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Load a user by their username.
	 * 
	 * This method is used by Spring Security for authentication.
	 * 
	 * @param username the username.
	 * @return the user details.
	 * @throws UsernameNotFoundException if the user is not found.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User dataBaseUser = userService.getByUsername(username);
		if (dataBaseUser == null) {
			throw new UsernameNotFoundException("Username or password unknow");
		}
		return new org.springframework.security.core.userdetails.User(dataBaseUser.getUsername(),
				dataBaseUser.getPassword(), getGrantedAuthorities(dataBaseUser.getRole()));
	}

	/**
	 * Get the user's roles.
	 * 
	 * This method converts a role into a list of `GrantedAuthority` objects that
	 * Spring Security uses to manage access authorization.
	 *
	 * @param role the user's role.
	 * @return a list of authorities.
	 */
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
}
