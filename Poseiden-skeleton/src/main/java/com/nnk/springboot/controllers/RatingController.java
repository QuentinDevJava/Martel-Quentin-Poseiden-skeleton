package com.nnk.springboot.controllers;

import static com.nnk.springboot.constants.AppConstants.RATINGS;
import static com.nnk.springboot.constants.AppConstants.RATING_ADD;
import static com.nnk.springboot.constants.AppConstants.RATING_UPDATE;
import static com.nnk.springboot.constants.AppConstants.REDIRECT_RATING_LIST;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for managing Rating entities. Allows displaying,
 * adding, updating, and deleting Rating.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class RatingController {

	/** The rating service. */
	private final RatingService ratingService;

	/**
	 * The HTTP servlet request used to retrieve the authenticated user's
	 * information.
	 */
	private final HttpServletRequest httpServletRequest;

	/**
	 * Displays the list of all Rating.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of Rating.
	 */
	@GetMapping("/rating/list")
	public String home(Model model) {
		List<Rating> ratingLists = ratingService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute(RATINGS, ratingLists);
		model.addAttribute("username", userConnect.getName());

		return "rating/list";
	}

	/**
	 * Displays the list of all Rating.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of Rating.
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return RATING_ADD;
	}

	/**
	 * Validate.
	 *
	 * @param rating the rating
	 * @param result the result
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.warn("rating formulaire erreur");
			return RATING_ADD;

		}
		ratingService.save(rating);
		return REDIRECT_RATING_LIST;
	}

	/**
	 * Show update form.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.getById(id);
		model.addAttribute("rating", rating);
		return RATING_UPDATE;
	}

	/**
	 * Update rating.
	 *
	 * @param id     the id
	 * @param rating the rating
	 * @param result the result
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			log.warn("rating formulaire erreur");
			return RATING_UPDATE;
		}

		rating.setId(id);
		ratingService.save(rating);
		List<Rating> ratings = ratingService.getAll();
		model.addAttribute(RATINGS, ratings);
		return REDIRECT_RATING_LIST;
	}

	/**
	 * Delete rating.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		ratingService.deleteById(id);
		List<Rating> ratings = ratingService.getAll();
		model.addAttribute(RATINGS, ratings);

		return REDIRECT_RATING_LIST;
	}
}
