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
	 * Displays the form for adding a new Rating.
	 *
	 * @param rating The empty {@link Rating} object to bind to the form.
	 * @return The name of the view displaying the add Rating form.
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return RATING_ADD;
	}

	/**
	 * Validates and saves a new Rating if no errors are present.
	 *
	 * @param rating The {@link Rating} object to save.
	 * @param result The result of binding the form data to the Rating object.
	 * @return The redirect URL to the Rating list or the add form in case of
	 *         validation errors.
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("rating formulaire erreur");
			return RATING_ADD;

		}
		ratingService.save(rating);
		return REDIRECT_RATING_LIST;
	}

	/**
	 * Displays the form for updating an existing Rating.
	 *
	 * @param id    The ID of the Rating to update.
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the update form for the Rating.
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.getById(id);
		model.addAttribute("rating", rating);
		return RATING_UPDATE;
	}

	/**
	 * Updates an existing Rating after validation.
	 *
	 * @param id     The ID of the Rating to update.
	 * @param rating The {@link Rating} object containing the updated data.
	 * @param result The result of binding the form data to the Rating object.
	 * @return The redirect URL to the list of Rating or the update form in case of
	 *         validation errors.
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("rating formulaire erreur");
			return RATING_UPDATE;
		}

		rating.setId(id);
		ratingService.save(rating);
		return REDIRECT_RATING_LIST;
	}

	/**
	 * Deletes a Rating by its ID.
	 *
	 * @param id The ID of the Rating to delete.
	 * @return The redirect URL to the list of remaining Rating.
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		ratingService.deleteById(id);
		return REDIRECT_RATING_LIST;
	}
}
