package com.nnk.springboot;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControlleurTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	void loginPageSpringTest() throws Exception {
		mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void userLoginTest() throws Exception {
		mockMvc.perform(formLogin("/login").user("userTest").password("userTest")).andExpect(authenticated());
	}

	@Test
	void userLoginFailedTest() throws Exception {
		mockMvc.perform(formLogin("/login").user("userTest").password(bCryptPasswordEncoder.encode("error")))
				.andDo(print()).andExpect(unauthenticated());
	}

	@WithMockUser(username = "adminTest", roles = { "USER", "ADMIN" })
	void accessPageWithAdminRole() throws Exception {
		mockMvc.perform(get("/user/add")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "userTest", roles = { "USER" })
	void accessPageWithUserRole() throws Exception {
		mockMvc.perform(get("/user/add")).andDo(print()).andExpect(status().isForbidden());
	}

	@Test
	void accessPageWithoutAuthentication() throws Exception {
		mockMvc.perform(get("/user/add")).andDo(print()).andExpect(status().isFound())
				.andExpect(redirectedUrl("http://localhost/login"));
	}
}
