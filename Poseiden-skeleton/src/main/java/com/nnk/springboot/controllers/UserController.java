package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

import jakarta.validation.Valid;

/**
 * The Class UserController.
 */
@Controller
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/user/list")
	public String home(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}

	/**
	 * Adds the user.
	 *
	 * @param bid the bid
	 * @return the string
	 */
	@GetMapping("/user/add")
	public String addUser(User bid) {
		return "user/add";
	}

	/**
	 * Validate.
	 *
	 * @param user   the user
	 * @param result the result
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/add";
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		userService.addUser(user);
		model.addAttribute("users", userService.findAll());
		return "redirect:/user/list";

	}

	/**
	 * Show update form.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id);

		user.setPassword("");
		model.addAttribute("user", user);
		return "user/update";
	}

	/**
	 * Update user.
	 *
	 * @param id     the id
	 * @param user   the user
	 * @param result the result
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/update";
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setPassword(user.getPassword());
		user.setId(id);
		userService.save(user);
		model.addAttribute("users", userService.findAll());
		return "redirect:/user/list";
	}

	/**
	 * Delete user.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id);
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "redirect:/user/list";
	}
}
