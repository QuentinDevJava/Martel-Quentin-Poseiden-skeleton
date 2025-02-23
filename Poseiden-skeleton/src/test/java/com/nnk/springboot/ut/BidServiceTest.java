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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidService;

@SpringBootTest
class BidServiceTest {

	@Mock
	private BidListRepository bidListRepository;

	@InjectMocks
	private BidService bidService;

	BidList bid1;
	BidList bid2;

	List<BidList> bids = new ArrayList<>();

	@BeforeEach
	void setUp() {
		bid1 = new BidList("Account test", "Type test", 10.00);
		bid2 = new BidList("Account test2", "Type test2", 20.00);
	}

	@Test
	void testGetAll() {
		// Arrange
		bids.add(bid1);
		bids.add(bid2);

		when(bidListRepository.findAll()).thenReturn(bids);

		// Act
		bidService.getAll();

		// Assert
		verify(bidListRepository, times(1)).findAll();
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		bidService.save(bid1);

		// Assert
		verify(bidListRepository, times(1)).save(bid1);
	}

	@Test
	void testGetById() {
		// Arrange
		when(bidListRepository.findById(1)).thenReturn(Optional.of(bid1));

		// Act
		bidService.getById(1);

		// Assert

		verify(bidListRepository, times(1)).findById(1);
	}

	@Test
	void testGetByIdNotFound() {
		// Arrange
		when(bidListRepository.findById(1)).thenReturn(Optional.empty());

		// Act
		assertThrows(IllegalArgumentException.class, () -> {
			bidService.getById(1);
		});
		// Assert
		verify(bidListRepository, times(1)).findById(1);
	}

	@Test
	void testDeleteById() {
		// Act
		bidService.deleteById(1);

		// Assert
		verify(bidListRepository, times(1)).deleteById(1);
	}

	@Test
	void testGetByAccount() {
		// Act
		bidService.getByAccount(bid1.getAccount());

		// Assert
		verify(bidListRepository, times(1)).findByAccount(bid1.getAccount());
	}
}
