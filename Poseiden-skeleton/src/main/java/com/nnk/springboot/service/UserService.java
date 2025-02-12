package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

}
