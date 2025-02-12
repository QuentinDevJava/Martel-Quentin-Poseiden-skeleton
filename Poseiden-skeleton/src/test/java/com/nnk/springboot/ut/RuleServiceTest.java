package com.nnk.springboot.ut;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

@SpringBootTest
class RuleServiceTest {

	@Mock
	private RuleNameRepository ruleNameRepository;

	@InjectMocks
	private RuleNameService ruleNameService;

	RuleName rule1;
	RuleName rule2;

	List<RuleName> ruleNames = new ArrayList<>();

	@BeforeEach
	void setUp() {
		rule1 = new RuleName("Rule Name1", "Description1", "Json1", "Template1", "SQL1", "SQL Part1");
		rule2 = new RuleName("Rule Name2", "Description2", "Json2", "Template2", "SQL2", "SQL Part2");

	}

	@Test
	void testGetAll() {
		// Arrange
		ruleNames.add(rule1);
		ruleNames.add(rule2);

		when(ruleNameRepository.findAll()).thenReturn(ruleNames);

		// Act
		ruleNameService.getAll();

		// Assert
		verify(ruleNameRepository, times(1)).findAll();
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		ruleNameService.save(rule1);

		// Assert
		verify(ruleNameRepository, times(1)).save(rule1);
	}

	@Test
	void testGetById() {
		// Arrange
		when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule1));

		// Act
		ruleNameService.getById(1);

		// Assert

		verify(ruleNameRepository, times(1)).findById(1);
	}

	@Test
	void testGetByIdNotFound() {
		// Arrange
		when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

		// Act
		assertThrows(IllegalArgumentException.class, () -> {
			ruleNameService.getById(1);
		});
		// Assert
		verify(ruleNameRepository, times(1)).findById(1);
	}

	@Test
	void testDeleteById() {
		// Act
		ruleNameService.deleteById(1);

		// Assert
		verify(ruleNameRepository, times(1)).deleteById(1);
	}
}
