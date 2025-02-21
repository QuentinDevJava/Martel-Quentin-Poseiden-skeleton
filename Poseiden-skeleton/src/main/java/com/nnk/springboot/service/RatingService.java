package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class RatingService.
 */
@RequiredArgsConstructor
@Service
public class RatingService {

	/** The rating repository. */
	private final RatingRepository ratingRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}

	/**
	 * Save.
	 *
	 * @param rating the rating
	 */
	public void save(Rating rating) {
		ratingRepository.save(rating);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Rating getById(Integer id) {
		return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Integer id) {
		ratingRepository.deleteById(id);
	}

	/**
	 * Gets the by moodys rating.
	 *
	 * @param moodysRating the moodys rating
	 * @return the by moodys rating
	 */
	public Rating getByMoodysRating(String moodysRating) {
		return ratingRepository.findByMoodysRating(moodysRating);
	}

}
