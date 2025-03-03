package com.nnk.springboot.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = { "USER" })
class GlobalErrorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test404Page() throws Exception {
		mockMvc.perform(get("/bidList/test404Status")).andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	@WithMockUser(username = "toto")
	void test403Page() throws Exception {
		mockMvc.perform(get("/user/list")).andExpect(status().isForbidden()).andDo(print());
	}

}
