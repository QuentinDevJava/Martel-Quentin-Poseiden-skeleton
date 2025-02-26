package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class RatingService.
 *
 * <p>
 * Service class responsible for managing {@link Rating} entities.
 * </p>
 * 
 * <p>
 * <b>Key Methods:</b>
 * </p>
 * <ul>
 * <li>{@link #getAll()} - Retrieves all ratings from the repository.</li>
 * <li>{@link #save(Rating)} - Saves a new or existing rating to the
 * repository.</li>
 * <li>{@link #getById(Integer)} - Retrieves a rating by its ID.</li>
 * <li>{@link #deleteById(Integer)} - Deletes a rating by its ID.</li>
 * <li>{@link #getByMoodysRating(String)} - Retrieves a rating by its Moodys
 * rating.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Service
public class RatingService {

	/** The rating repository. */
	private final RatingRepository ratingRepository;

	/**
	 * Gets all {@link Rating}.
	 *
	 * @return the list of all {@link Rating} entities
	 */
	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}

	/**
	 * Save a {@link Rating}.
	 * 
	 * @param rating the {@link Rating} object to be saved
	 */
	public void save(Rating rating) {
		ratingRepository.save(rating);
	}

	/**
	 * Gets the {@link Rating} by id.
	 *
	 * @param id the ID of the rating to retrieve
	 * @return the {@link Rating} object with the specified ID
	 * @throws IllegalArgumentException if no rating with the given ID is found
	 */
	public Rating getById(Integer id) {
		return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
	}

	/**
	 * Delete {@link Rating} by id.
	 *
	 * @param id the ID of the rating to delete
	 */
	public void deleteById(Integer id) {
		ratingRepository.deleteById(id);
	}

	/**
	 * Gets the {@link Rating} by moodys rating.
	 *
	 * @param moodysRating the Moodys rating to search for
	 * @return the {@link Rating} object for the given Moodys rating, or
	 *         {@code null} if not found
	 */
	public Rating getByMoodysRating(String moodysRating) {
		return ratingRepository.findByMoodysRating(moodysRating);
	}
}
