package com.nnk.springboot.ut;

import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@SpringBootTest
class TradeServiceTest {

	@Mock
	private TradeRepository tradeRepository;

	@InjectMocks
	private TradeService tradeService;

	Trade trade1;
	Trade trade2;

	List<Trade> trades = new ArrayList<>();

	@BeforeEach
	void setUp() {
		trade1 = new Trade("test1", "test1");
		trade2 = new Trade("test2", "test2");

	}

	@Test
	void testGetAll() {
		// Arrange
		trades.add(trade1);
		trades.add(trade2);

		when(tradeRepository.findAll()).thenReturn(trades);

		// Act
		tradeService.getAll();

		// Assert
		verify(tradeRepository, times(1)).findAll();
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		tradeService.save(trade1);

		// Assert
		verify(tradeRepository, times(1)).save(trade1);
	}

	@Test
	void testGetById() {
		// Arrange
		when(tradeRepository.findById(1)).thenReturn(Optional.of(trade1));

		// Act
		tradeService.getById(1);

		// Assert

		verify(tradeRepository, times(1)).findById(1);
	}

	@Test
	void testGetByIdNotFound() {
		// Arrange
		when(tradeRepository.findById(1)).thenReturn(Optional.empty());

		// Act
		Trade result = tradeService.getById(1);

		// Assert
		assertNull(result);
		verify(tradeRepository, times(1)).findById(1);
	}

	@Test
	void testDeleteById() {
		// Act
		tradeService.deleteById(1);

		// Assert
		verify(tradeRepository, times(1)).deleteById(1);
	}
}
