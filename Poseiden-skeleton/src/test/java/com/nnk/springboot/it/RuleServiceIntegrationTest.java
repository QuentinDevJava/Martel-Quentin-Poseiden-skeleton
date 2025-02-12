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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

@SpringBootTest
public class RuleServiceIntegrationTest {

	@Autowired
	private RuleNameService ruleNameService;

	@Autowired
	private RuleNameRepository ruleNameRepository;

	RuleName rule1;
	RuleName rule2;

	List<RuleName> ruleNames = new ArrayList<>();

	@BeforeEach
	void setUp() {
		rule1 = new RuleName("Rule Name1", "Description1", "Json1", "Template1", "SQL1", "SQL Part1");
		rule2 = new RuleName("Rule Name2", "Description2", "Json2", "Template2", "SQL2", "SQL Part2");
		ruleNameRepository.deleteAll();
	}

	@Test
	void testGetAll() {
		// Arrange

		ruleNameRepository.save(rule1);
		ruleNameRepository.save(rule2);

		// Act
		List<RuleName> result = ruleNameService.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		ruleNameService.save(rule1);

		// Assert
		assertTrue(ruleNameRepository.existsById(rule1.getId()));
	}

	@Test
	void testGetById() {
		// Arrange
		ruleNameRepository.save(rule1);

		// Act
		RuleName result = ruleNameService.getById(rule1.getId());

		// Assert
		assertNotNull(result);
		assertEquals("Rule Name1", result.getName());
	}

	@Test
	void testGetByIdError() {
		int invalidId = 99999999;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			ruleNameService.getById(invalidId);
		});

		assertEquals("Invalid RuleName Id:" + invalidId, exception.getMessage());
	}

	@Test
	void testDeleteById() {
		// Arrange
		ruleNameRepository.save(rule1);

		// Act
		ruleNameService.deleteById(1);

		// Assert
		assertFalse(ruleNameRepository.existsById(1));
	}
}
