package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Rating;

/**
 * The Interface RatingRepository.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	/**
	 * Find by moodys rating.
	 *
	 * @param moodysRating the moodys rating
	 * @return the rating
	 */
	Rating findByMoodysRating(String moodysRating);

}
