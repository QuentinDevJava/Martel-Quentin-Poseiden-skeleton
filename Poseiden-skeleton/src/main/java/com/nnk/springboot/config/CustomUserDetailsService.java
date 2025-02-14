package com.nnk.springboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomUserDetailsService.
 */
@Profile("prod")
@Service
public class CustomUserDetailsService implements UserDetailsService {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User dataBaseUser = userService.getByUsername(username);
		return new org.springframework.security.core.userdetails.User(dataBaseUser.getUsername(),
				dataBaseUser.getPassword(), getGrantedAuthorities(dataBaseUser.getRole()));
	}

	/**
	 * Gets the granted authorities.
	 *
	 * @param role the role
	 * @return the granted authorities
	 */
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
}
