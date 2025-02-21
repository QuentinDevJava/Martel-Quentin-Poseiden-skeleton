package com.nnk.springboot.controllers;

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
 * The Class RatingController.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class RatingController {

	/** The rating service. */
	private final RatingService ratingService;

	/** The http servlet request. */
	private final HttpServletRequest httpServletRequest;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/rating/list")
	public String home(Model model) {
		List<Rating> ratingLists = ratingService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute("ratings", ratingLists);
		model.addAttribute("username", userConnect.getName());

		return "rating/list";
	}

	/**
	 * Adds the rating form.
	 *
	 * @param rating the rating
	 * @return the string
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
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
			return "rating/add";

		}
		ratingService.save(rating);
		return "redirect:/rating/list";
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
		return "rating/update";
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
			log.warn("bidList formulaire erreur");
			return "rating/update";
		}

		rating.setId(id);
		ratingService.save(rating);
		List<Rating> ratings = ratingService.getAll();
		model.addAttribute("ratings", ratings);
		return "redirect:/rating/list";
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
		model.addAttribute("ratings", ratings);

		return "redirect:/rating/list";
	}
}
