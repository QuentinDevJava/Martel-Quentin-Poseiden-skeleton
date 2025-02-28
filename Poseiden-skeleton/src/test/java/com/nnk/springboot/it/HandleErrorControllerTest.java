package com.nnk.springboot.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = { "USER" })
class HandleErrorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testHandleError_403() throws Exception {

		mockMvc.perform(get("/home"))

				.andDo(print())

				.andExpect(status().isForbidden())

				.andExpect(view().name("error"));

//				.andExpect(model().attribute("errorMsg", "You are not authorized to access the requested data."))
//
//				.andExpect(model().attribute("errorTitle", "Access Denied Exception"))
//
//				.andExpect(model().attribute("username", "testUser"));
	}

}
