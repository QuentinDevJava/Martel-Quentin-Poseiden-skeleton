package com.nnk.springboot.ut;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;

@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	User user1;
	User user2;

	List<User> users = new ArrayList<>();

	@BeforeEach
	void setUp() {
		user1 = new User("userTest1", "userTest1", "Password1!@&Test1", "USER");
		user2 = new User("userTest2", "userTest2", "Password1!@&Test2", "ADMIN");
	}

	@Test
	void testGetAll() {
		// Arrange
		users.add(user1);
		users.add(user2);

		when(userRepository.findAll()).thenReturn(users);

		// Act
		userService.getAll();

		// Assert
		verify(userRepository, times(1)).findAll();
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		userService.save(user1);

		// Assert
		verify(userRepository, times(1)).save(user1);
	}

	@Test
	void testAddUser() {
		// Arrange
		when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

		// Act
		userService.addUser(user1);

		// Assert
		verify(userRepository, times(1)).save(user1);
	}

	@Test
	void testGetById() {
		// Arrange
		when(userRepository.findById(1)).thenReturn(Optional.of(user1));

		// Act
		userService.getById(1);

		// Assert

		verify(userRepository, times(1)).findById(1);
	}

	@Test
	void testGetByIdNotFound() {
		// Arrange
		when(userRepository.findById(1)).thenReturn(Optional.empty());

		// Act
		assertThrows(IllegalArgumentException.class, () -> {
			userService.getById(1);
		});

		// Assert
		verify(userRepository, times(1)).findById(1);
	}

	@Test
	void testDeleteById() {
		// Act
		userService.deleteById(1);

		// Assert
		verify(userRepository, times(1)).deleteById(1);
	}

	@Test
	void testGetByUsername() {
		// Arrange

		// Act
		userService.getByUsername(user1.getUsername());

		// Assert
		verify(userRepository, times(1)).findByUsername(user1.getUsername());
	}

	@Test
	void testIsAdminTrue() {
		// Arrange
		when(userRepository.findByUsername(user2.getUsername())).thenReturn(user2);

		// Act
		assertTrue(userService.isAdmin(user2.getUsername()));

	}

	@Test
	void testIsAdminFalse() {
		// Arrange
		when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

		// Act
		assertFalse(userService.isAdmin(user1.getUsername()));

	}

}
