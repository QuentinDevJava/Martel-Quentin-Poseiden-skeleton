package com.nnk.springboot.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

@SpringBootTest
public class RatingServiceIntegrationTest {

	@Autowired
	private RatingService ratingService;

	@Autowired
	private RatingRepository ratingRepository;

	Rating rating1;
	Rating rating2;

	List<Rating> ratings = new ArrayList<>();

	@BeforeEach
	void setUp() {
		rating1 = new Rating("Moodys Rating1", "Sand PRating1", "Fitch Rating1", 10);
		rating2 = new Rating("Moodys Rating2", "Sand PRating2", "Fitch Rating2", 11);
		ratingRepository.deleteAll();
	}

	@Test
	void testGetAll() {
		// Arrange

		ratingRepository.save(rating1);
		ratingRepository.save(rating2);

		// Act
		List<Rating> result = ratingService.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		ratingService.save(rating1);

		// Assert
		assertTrue(ratingRepository.existsById(rating1.getId()));
	}

	@Test
	void testGetById() {
		// Arrange
		ratingRepository.save(rating1);

		// Act
		Rating result = ratingService.getById(rating1.getId());

		// Assert
		assertNotNull(result);
		assertEquals(10, result.getOrderNumber());
	}

	@Test
	void testDeleteById() {
		// Arrange
		ratingRepository.save(rating1);

		// Act
		ratingService.deleteById(1);

		// Assert
		assertFalse(ratingRepository.existsById(1));
	}
}
