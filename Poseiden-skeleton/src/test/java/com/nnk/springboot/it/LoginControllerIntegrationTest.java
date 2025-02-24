package com.nnk.springboot.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LoginControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void testLogin() throws Exception {

		mockMvc.perform(get("/login"))

				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "testuser", roles = { "ADMIN" })
	void testGetAllUserArticles() throws Exception {

		mockMvc.perform(get("/secure/article-details"))

				.andExpect(status().isOk())

				.andDo(print())

				.andExpect(model().attributeExists("users"))

				.andExpect(view().name("user/list"));
	}

}
