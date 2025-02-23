package com.nnk.springboot.ut;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest
class UserTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void userTest() {

		User user = new User("userTest", "userTest", "Password1!@&Test", "USER");
		// Save
		user = userRepository.save(user);
		Assertions.assertNotNull(user.getId());
		Assertions.assertEquals("userTest", user.getUsername());

		// Update
		user.setUsername("UserUpdate");
		user = userRepository.save(user);
		Assertions.assertEquals("UserUpdate", user.getUsername());

		// Find
		List<User> listResult = userRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = user.getId();
		userRepository.delete(user);
		Optional<User> userList = userRepository.findById(id);
		Assertions.assertFalse(userList.isPresent());
	}
}
