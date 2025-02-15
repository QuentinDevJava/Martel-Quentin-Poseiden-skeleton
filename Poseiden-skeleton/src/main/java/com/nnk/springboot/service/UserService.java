package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * The Class UserService.
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
	public void delete(User user) {
		userRepository.delete(user);
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
