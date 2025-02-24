package com.nnk.springboot.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;

@SpringBootTest
class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	User user1;
	User user2;

	List<User> users = new ArrayList<>();

	@BeforeEach
	void setUp() {
		user1 = new User("userTest1", "userTest1", "Password1!@&Test1", "USER");
		user2 = new User("userTest2", "userTest2", "Password1!@&Test2", "USER");
		userRepository.deleteAll();
	}

	@Test
	void testGetAll() {
		// Arrange

		userRepository.save(user1);
		userRepository.save(user2);

		// Act
		List<User> result = userService.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		userService.save(user1);

		// Assert
		assertTrue(userRepository.existsById(user1.getId()));
	}

	@Test
	void testGetById() {
		// Arrange
		userRepository.save(user1);

		// Act
		User result = userService.getById(user1.getId());

		// Assert
		assertNotNull(result);
		assertEquals("userTest1", result.getUsername());
	}

	@Test
	void testGetByIdError() {
		int invalidId = 99999999;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			userService.getById(invalidId);
		});

		assertEquals("Invalid user ID: " + invalidId, exception.getMessage());
	}

	@Test
	void testDeleteById() {
		// Arrange
		userRepository.save(user1);

		// Act
		userService.deleteById(1);

		// Assert
		assertFalse(userRepository.existsById(1));
	}
}
