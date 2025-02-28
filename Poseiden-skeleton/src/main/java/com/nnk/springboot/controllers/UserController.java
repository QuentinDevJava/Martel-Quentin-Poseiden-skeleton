package com.nnk.springboot.controllers;

import static com.nnk.springboot.constants.AppConstants.REDIRECT_USER_LIST;
import static com.nnk.springboot.constants.AppConstants.USERS;
import static com.nnk.springboot.constants.AppConstants.USER_ADD;
import static com.nnk.springboot.constants.AppConstants.USER_UPDATE;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller responsible for managing User entities. Allows displaying, adding,
 * updating, and deleting User.
 */
@Controller
@RequiredArgsConstructor
public class UserController {

	/** The user service. */
	private final UserService userService;

	/**
	 * Displays the list of all User.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of User.
	 */
	@GetMapping("/user/list")
	public String home(Model model) {
		model.addAttribute(USERS, userService.getAll());
		return "user/list";
	}

	/**
	 * Displays the list of all User.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of User.
	 */
	@GetMapping("/user/add")
	public String addUser(User bid) {
		return USER_ADD;
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
			return USER_ADD;
		}

		if (userService.addUser(user)) {
			model.addAttribute(USERS, userService.getAll());
			return REDIRECT_USER_LIST;
		}
		result.rejectValue("username", "error.user", "The username: " + user.getUsername() + " is used");
		return USER_ADD;

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
		User user = userService.getById(id);

		user.setPassword("");
		model.addAttribute("user", user);
		return USER_UPDATE;
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
			return USER_UPDATE;
		}
		user.setId(id);
		userService.save(user);
		model.addAttribute(USERS, userService.getAll());
		return REDIRECT_USER_LIST;
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
		userService.deleteById(id);
		model.addAttribute(USERS, userService.getAll());
		return REDIRECT_USER_LIST;
	}
}
