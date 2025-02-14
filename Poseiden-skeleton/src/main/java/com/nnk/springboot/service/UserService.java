package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service
public class UserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

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

}
