package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * Service class for managing users.
 * <p>
 * <b>Methods:</b>
 * </p>
 * <ul>
 * <li><b>{@link #getByUsername} :</b> Retrieves a user by their username.</li>
 * <li><b>{@link #findAll} :</b> Retrieves all users from the system.</li>
 * <li><b>{@link #save} :</b> Saves a new user or updates an existing user.</li>
 * <li><b>{@link #findById} :</b> Retrieves a user by their ID.</li>
 * <li><b>{@link #deleteById} :</b> Deletes a user by their ID.</li>
 * <li><b>{@link #addUser} :</b> Adds a new user after validating the
 * username.</li>
 * <li><b>{@link #userIsValide} :</b> Validates if the username is
 * available.</li>
 * <li><b>{@link #isAdmin} :</b> Checks if the user has the "ADMIN" role.</li>
 * </ul>
 */
@Service
public class UserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Gets the by username.
	 *
	 * @param username the username
	 * @return the by username
	 */
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User findById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}

	/**
	 * Delete.
	 *
	 * @param user the user
	 */
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	public boolean addUser(User user) {
		if (userIsValide(user.getUsername())) {
			save(user);
			return true;
		}
		return false;
	}

	private boolean userIsValide(String username) {
		User user = getByUsername(username);
		return user == null;
	}

	public boolean isAdmin(StringBuilder username) {
		User user = getByUsername(username.toString());
		return "ADMIN".equals(user.getRole());
	}

}
