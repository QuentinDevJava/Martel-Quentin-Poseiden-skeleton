package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Rating;

// TODO: Auto-generated Javadoc
/**
 * The Interface RatingRepository.
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	/**
	 * Find by moodys rating.
	 *
	 * @param moodysRating the moodys rating
	 * @return the rating
	 */
	Rating findByMoodysRating(String moodysRating);

}
