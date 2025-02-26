package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Rating;

/**
 * The Interface RatingRepository.
 * 
 * <p>
 * Repository interface for accessing {@link Rating} entities from the database.
 * This interface extends {@link JpaRepository} for basic CRUD operations.
 * </p>
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	/**
	 * Find by moodys rating.
	 *
	 * @param moodysRating the moodys rating
	 * @return the {@link Rating} or null no rating is found
	 */
	Rating findByMoodysRating(String moodysRating);

}
