package com.nnk.springboot.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class HomeControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@WithMockUser(username = "testUser", roles = { "USER" })
	void testHomeUserRole() throws Exception {

		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(model().attribute("username", "testUser")).andExpect(model().attribute("adminRole", false));
	}

	@Test
	@WithMockUser(username = "testAdmin", roles = { "ADMIN" })
	void testHomeAdminRole() throws Exception {

		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(model().attribute("username", "testAdmin")).andExpect(model().attribute("adminRole", true));
	}

}
