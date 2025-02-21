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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@SpringBootTest
class TradeServiceIntegrationTest {

	@Autowired
	private TradeService tradeService;

	@Autowired
	private TradeRepository tradeRepository;

	Trade trade1;
	Trade trade2;

	List<Trade> trades = new ArrayList<>();

	@BeforeEach
	void setUp() {
		trade1 = new Trade("test1", "test1", 10.00);
		trade2 = new Trade("test2", "test2", 10.00);
		tradeRepository.deleteAll();
	}

	@Test
	void testGetAll() {
		// Arrange

		tradeRepository.save(trade1);
		tradeRepository.save(trade2);

		// Act
		List<Trade> result = tradeService.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		tradeService.save(trade1);

		// Assert
		assertTrue(tradeRepository.existsById(trade1.getTradeId()));
	}

	@Test
	void testGetById() {
		// Arrange
		tradeRepository.save(trade1);

		// Act
		Trade result = tradeService.getById(trade1.getTradeId());

		// Assert
		assertNotNull(result);
		assertEquals("test1", result.getAccount());
	}

	@Test
	void testGetByIdError() {
		int invalidId = 99999999;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			tradeService.getById(invalidId);
		});

		assertEquals("Invalid Trade Id:" + invalidId, exception.getMessage());
	}

	@Test
	void testDeleteById() {
		// Arrange
		tradeRepository.save(trade1);

		// Act
		tradeService.deleteById(1);

		// Assert
		assertFalse(tradeRepository.existsById(1));
	}
}
