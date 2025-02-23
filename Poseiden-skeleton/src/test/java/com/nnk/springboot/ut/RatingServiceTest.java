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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

@SpringBootTest
class RatingServiceTest {

	@Mock
	private RatingRepository ratingRepository;

	@InjectMocks
	private RatingService ratingService;

	Rating rating1;
	Rating rating2;

	List<Rating> ratings = new ArrayList<>();

	@BeforeEach
	void setUp() {
		rating1 = new Rating("Test1", "Test1", "Test1", 20);
		rating2 = new Rating("Test2", "Test2", "Test2", 21);

	}

	@Test
	void testGetAll() {
		// Arrange
		ratings.add(rating1);
		ratings.add(rating2);

		when(ratingRepository.findAll()).thenReturn(ratings);

		// Act
		ratingService.getAll();

		// Assert
		verify(ratingRepository, times(1)).findAll();
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		ratingService.save(rating1);

		// Assert
		verify(ratingRepository, times(1)).save(rating1);
	}

	@Test
	void testGetById() {
		// Arrange
		when(ratingRepository.findById(1)).thenReturn(Optional.of(rating1));

		// Act
		ratingService.getById(1);

		// Assert

		verify(ratingRepository, times(1)).findById(1);
	}

	@Test
	void testGetByIdNotFound() {
		// Arrange
		when(ratingRepository.findById(1)).thenReturn(Optional.empty());

		// Act
		assertThrows(IllegalArgumentException.class, () -> {
			ratingService.getById(1);
		});
		// Assert
		verify(ratingRepository, times(1)).findById(1);
	}

	@Test
	void testDeleteById() {
		// Act
		ratingService.deleteById(1);

		// Assert
		verify(ratingRepository, times(1)).deleteById(1);
	}

	@Test
	void testGetByMoodysRating() {
		// Act
		ratingService.getByMoodysRating(rating1.getMoodysRating());

		// Assert
		verify(ratingRepository, times(1)).findByMoodysRating(rating1.getMoodysRating());
	}
}
