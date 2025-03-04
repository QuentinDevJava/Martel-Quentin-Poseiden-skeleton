package com.nnk.springboot.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = { "ADMIN" })
class UserControllerIntegrationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	User user1;
	User user2;

	List<User> users = new ArrayList<>();

	@BeforeEach
	void setUp() {
		user1 = new User("userTest1", "userTest1", "Password1!@&Test1", "ADMIN");
		user2 = new User("userTest2", "userTest2", "Password1!@&Test2", "ADMIN");
		userRepository.deleteAll();
	}

	@Test
	void testGetUserList() throws Exception {
		userService.save(user1);
		userService.save(user2);

		mockMvc.perform(get("/user/list"))

				.andExpect(status().isOk())

				.andDo(print())

				.andExpect(view().name("user/list"))

				.andExpect(model().attributeExists("users"));
	}

	@Test
	void testAddUserForm() throws Exception {
		mockMvc.perform(get("/user/add"))

				.andExpect(status().isOk())

				.andExpect(view().name("user/add"));
	}

	@Test
	void testValidateUser() throws Exception {

		User userTest = userService.getByUsername(user1.getUsername());
		assertNull(userTest);

		mockMvc.perform(post("/user/validate")

				.param("fullname", user1.getFullname())

				.param("username", user1.getUsername())

				.param("password", user1.getPassword())

				.param("role", user1.getRole())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(redirectedUrl("/user/list"));

		userTest = userService.getByUsername(user1.getUsername());

		assertNotNull(user1);

		assertEquals(user1.getUsername(), userTest.getUsername());

	}

	@Test
	void testValidateUserErrorUsernameExist() throws Exception {

		userService.save(user1);

		mockMvc.perform(post("/user/validate")

				.param("fullname", user1.getFullname())

				.param("username", user1.getUsername())

				.param("password", user1.getPassword())

				.param("role", user1.getRole())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isOk())

				.andExpect(view().name("user/add"))

				.andExpect(model().hasErrors()).andExpect(model().errorCount(1));

	}

	@Test
	void testValidateUserErrorForm() throws Exception {

		mockMvc.perform(post("/user/validate")

				.param("fullname", "")

				.param("username", user1.getUsername())

				.param("password", user1.getPassword())

				.param("role", user1.getRole())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isOk())

				.andExpect(view().name("user/add"));

		User userTest = userService.getByUsername(user1.getUsername());

		assertNull(userTest);

	}

	@Test
	void testShowUpdateForm() throws Exception {
		userService.save(user1);
		user1 = userService.getByUsername(user1.getUsername());
		int userId = user1.getId();

		mockMvc.perform(get("/user/update/{id}", userId))

				.andExpect(status().isOk())

				.andExpect(model().attributeExists("user"))

				.andExpect(view().name("user/update"));

	}

	@Test
	void testUpdateUser() throws Exception {

		userService.save(user1);
		user1 = userService.getByUsername(user1.getUsername());
		int userId = user1.getId();

		user1.setFullname("Test");

		mockMvc.perform(post("/user/update/{id}", userId)

				.param("fullname", user1.getFullname())

				.param("username", user1.getUsername())

				.param("password", user1.getPassword())

				.param("role", user1.getRole())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(redirectedUrl("/user/list"));

		User userTest = userService.getById(userId);

		assertEquals("Test", userTest.getFullname());

	}

	@Test
	void testUpdateUserError() throws Exception {

		userService.save(user1);
		user1 = userService.getByUsername(user1.getUsername());
		int userId = user1.getId();

		mockMvc.perform(post("/user/update/{id}", userId)

				.param("fullname", "")

				.param("username", user1.getUsername())

				.param("password", user1.getPassword())

				.param("role", user1.getRole())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isOk())

				.andExpect(view().name("user/update"));

		User userTest = userService.getById(userId);

		assertEquals("userTest1", userTest.getFullname());

	}

	@Test
	void testDeleteUser() throws Exception {
		userService.save(user1);
		user1 = userService.getByUsername(user1.getUsername());
		int userId = user1.getId();

		mockMvc.perform(get("/user/delete/{id}", userId))

				.andExpect(status().isFound())

				.andDo(print())

				.andExpect(redirectedUrl("/user/list"));

		List<User> userTest = userService.getAll();
		assertTrue(userTest.isEmpty());
	}
}