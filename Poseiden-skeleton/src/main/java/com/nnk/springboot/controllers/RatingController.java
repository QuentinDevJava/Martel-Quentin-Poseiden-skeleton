package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RatingController {

	// TODO: Inject Rating service
	@Autowired
	private RatingService ratingService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@GetMapping("/rating/list")
	public String home(Model model) {
		// TODO: find all Rating, add to model

		List<Rating> ratingLists = ratingService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute("ratings", ratingLists);
		model.addAttribute("username", userConnect.getName());

		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Rating list

		if (result.hasErrors()) {
			log.warn("rating formulaire erreur");
		}

		ratingService.save(rating);

		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Rating by Id and to model then show to the form
		Rating rating = ratingService.getById(id);
//		if (rating == null) {
//			log.warn("rating est vide ou null");
//		}
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update Rating and
		// return Rating list
		if (result.hasErrors()) {
			log.warn("bidList formulaire erreur");
		}

		rating.setId(id);
		ratingService.save(rating);
		List<Rating> ratings = ratingService.getAll();
		model.addAttribute("ratings", ratings);
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Rating by Id and delete the Rating, return to Rating list
//		Rating rating = ratingService.getById(id);
//		if (rating == null) {
//			log.warn("rating est vide ou null");
//		}
		ratingService.deleteById(id);
		List<Rating> ratings = ratingService.getAll();
		model.addAttribute("ratings", ratings);

		return "redirect:/rating/list";
	}
}
