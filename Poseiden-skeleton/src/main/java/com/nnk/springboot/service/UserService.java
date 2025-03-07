package com.nnk.springboot.service;

import java.util.List;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class UserService.
 * 
 * Service class responsible for managing user operations. *
 * 
 * <p>
 * Service class responsible for managing {@link User} entities. *
 * </p>
 * 
 * <p>
 * <b>Key Methods:</b>
 * </p>
 * <ul>
 * <li>{@link #getByUsername(String)} - Retrieves a user by their username.</li>
 * <li>{@link #getAll()} - Retrieves all users from the system.</li>
 * <li>{@link #save(User)} - Saves or updates a user.</li>
 * <li>{@link #getById(Integer)} - Retrieves a user by their ID.</li>
 * <li>{@link #deleteById(Integer)} - Deletes a user by their ID.</li>
 * <li>{@link #addUser(User)} - Adds a new user if the username is not already
 * taken.</li>
 * <li>{@link #isAdmin(String)} - Checks if a user has the ADMIN role.</li>
 * <li>{@link #getUsernameLoginInfo(Authentication)} - Retrieves the username
 * from the authentication token for a standard user.</li>
 * <li>{@link #getOauth2LoginInfo(Authentication)} - Retrieves the login
 * information for an authenticated user via OAuth2.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	/**
	 * Retrieves a user by their username.
	 * 
	 * @param username the username to search for
	 * @return the user matching the username
	 * @throws IllegalArgumentException if the user does not exist
	 */
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Retrieves all users from the system.
	 * 
	 * @return a list of all users
	 */
	public List<User> getAll() {
		return userRepository.findAll();
	}

	/**
	 * Saves or updates a user. The password is automatically encrypted before
	 * saving.
	 * 
	 * @param user the user to save
	 */
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	/**
	 * Retrieves a user by their ID.
	 * 
	 * @param id the user ID to search for
	 * @return the user matching the ID
	 * @throws IllegalArgumentException if the ID is invalid
	 */
	public User getById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
	}

	/**
	 * Deletes a user by their ID.
	 * 
	 * @param id the ID of the user to delete
	 */
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	/**
	 * Adds a new user after validation.
	 * 
	 * @param user the user to add
	 * @return true if the user was added successfully, false if the username
	 *         already exists
	 */
	public boolean addUser(User user) {
		if (Objects.isNull(getByUsername(user.getUsername()))) {
			save(user);
			return true;
		}
		return false;
	}

	/**
	 * Checks if the provided user has the "ROLE_ADMIN" authority.
	 *
	 * @param userConnect the Authentication object representing the authenticated
	 *                    user.
	 * @return true if the user has "ROLE_ADMIN", false otherwise.
	 */
	public boolean isAdmin(Authentication userConnect) {
		return userConnect.getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
	}

}