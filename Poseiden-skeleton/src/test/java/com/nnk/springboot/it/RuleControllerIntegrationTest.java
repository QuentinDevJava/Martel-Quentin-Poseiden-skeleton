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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "testuser", roles = { "ADMIN" })
class RuleControllerIntegrationTest {

	@Autowired
	private RuleNameService ruleNameService;

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Autowired
	private MockMvc mockMvc;

	RuleName ruleName;
	RuleName ruleName2;

	List<BidList> bids = new ArrayList<>();

	@BeforeEach
	void setUp() {
		ruleName = new RuleName("Rule Name1", "Description1", "Json1", "Template1", "SQL1", "SQL Part1");
		ruleName2 = new RuleName("Rule Name2", "Description2", "Json2", "Template2", "SQL2", "SQL Part2");
		ruleNameRepository.deleteAll();
	}

	@Test
	void testGetRuleNameList() throws Exception {
		ruleNameService.save(ruleName);
		ruleNameService.save(ruleName2);

		mockMvc.perform(get("/ruleName/list"))

				.andExpect(status().isOk())

				.andDo(print())

				.andExpect(view().name("ruleName/list"))

				.andExpect(model().attributeExists("ruleNames"))

				.andExpect(model().attributeExists("username"));
	}

	@Test
	void testAddRuleNameForm() throws Exception {
		mockMvc.perform(get("/ruleName/add"))

				.andExpect(status().isOk())

				.andExpect(view().name("ruleName/add"));
	}

	@Test
	void testValidateRuleName() throws Exception {

		RuleName ruleNameTest = ruleNameService.getByName(ruleName.getName());
		assertNull(ruleNameTest);

		mockMvc.perform(post("/ruleName/validate")

				.param("name", ruleName.getName())

				.param("description", ruleName.getDescription())

				.param("json", ruleName.getJson())

				.param("template", ruleName.getTemplate())

				.param("sqlStr", ruleName.getSqlStr())

				.param("sqlPart", ruleName.getSqlPart())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(view().name("redirect:/ruleName/list"));

		ruleNameTest = ruleNameService.getByName(ruleName.getName());

		assertNotNull(ruleName);

		assertEquals(ruleName.getName(), ruleNameTest.getName());
		assertEquals(ruleName.getDescription(), ruleNameTest.getDescription());
		assertEquals(ruleName.getJson(), ruleNameTest.getJson());
		assertEquals(ruleName.getTemplate(), ruleNameTest.getTemplate());
		assertEquals(ruleName.getSqlStr(), ruleNameTest.getSqlStr());
		assertEquals(ruleName.getSqlPart(), ruleNameTest.getSqlPart());

	}

	@Test
	void testShowUpdateForm() throws Exception {
		ruleNameService.save(ruleName);
		ruleName = ruleNameService.getByName(ruleName.getName());
		int ruleNameId = ruleName.getId();

		mockMvc.perform(get("/ruleName/update/{id}", ruleNameId))

				.andExpect(status().isOk())

				.andExpect(model().attributeExists("ruleName"))

				.andExpect(view().name("ruleName/update"));

	}

	@Test
	void testUpdateRuleName() throws Exception {

		ruleNameService.save(ruleName);
		ruleName = ruleNameService.getByName(ruleName.getName());
		int ruleNameId = ruleName.getId();

		ruleName.setDescription("Test");

		mockMvc.perform(post("/ruleName/update/{id}", ruleNameId)

				.param("name", ruleName.getName())

				.param("description", ruleName.getDescription())

				.param("json", ruleName.getJson())

				.param("template", ruleName.getTemplate())

				.param("sqlStr", ruleName.getSqlStr())

				.param("sqlPart", ruleName.getSqlPart())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(redirectedUrl("/ruleName/list"));

		ruleName = ruleNameService.getById(ruleNameId);

		assertEquals("Test", ruleName.getDescription());

	}

	@Test
	void testDeleteRuleName() throws Exception {
		ruleNameService.save(ruleName);
		ruleName = ruleNameService.getByName(ruleName.getName());
		int ruleNameId = ruleName.getId();
		mockMvc.perform(get("/ruleName/delete/{id}", ruleNameId))

				.andExpect(status().isFound())

				.andDo(print())

				.andExpect(redirectedUrl("/ruleName/list"));

		List<RuleName> ruleNameTest = ruleNameService.getAll();
		assertTrue(ruleNameTest.isEmpty());
	}
}