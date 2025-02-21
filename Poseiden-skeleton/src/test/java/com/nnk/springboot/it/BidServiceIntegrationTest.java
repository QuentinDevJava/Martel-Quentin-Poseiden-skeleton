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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidService;

@SpringBootTest
class BidServiceIntegrationTest {

	@Autowired
	private BidService bidService;

	@Autowired
	private BidListRepository bidListRepository;

	BidList bid1;
	BidList bid2;

	List<BidList> bids = new ArrayList<>();

	@BeforeEach
	void setUp() {
		bid1 = new BidList("Account test", "Type test", 10.00);
		bid2 = new BidList("Account test2", "Type test2", 20.00);
		bidListRepository.deleteAll();
	}

	@Test
	void testGetAll() {
		// Arrange

		bidListRepository.save(bid1);
		bidListRepository.save(bid2);

		// Act
		List<BidList> result = bidService.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		bidService.save(bid1);

		// Assert
		assertTrue(bidListRepository.existsById(bid1.getBidListId()));
	}

	@Test
	void testGetById() {
		// Arrange
		bidListRepository.save(bid1);

		// Act
		BidList result = bidService.getById(bid1.getBidListId());

		// Assert
		assertNotNull(result);
		assertEquals("Account test", result.getAccount());
	}

	@Test
	void testGetByIdError() {
		int invalidId = 99999999;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			bidService.getById(invalidId);
		});

		assertEquals("Invalid BidList Id:" + invalidId, exception.getMessage());
	}

	@Test
	void testDeleteById() {
		// Arrange
		bidListRepository.save(bid1);

		// Act
		bidService.deleteById(1);

		// Assert
		assertFalse(bidListRepository.existsById(1));
	}
}
