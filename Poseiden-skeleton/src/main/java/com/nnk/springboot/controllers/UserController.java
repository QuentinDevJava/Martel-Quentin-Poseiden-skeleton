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
	 * Displays the form for adding a new User.
	 *
	 * @param user The empty {@link User} object to bind to the form.
	 * @return The name of the view displaying the add User form.
	 */
	@GetMapping("/user/add")
	public String addUser(User user) {
		return USER_ADD;
	}

	/**
	 * Validates and saves a new User if no errors are present.
	 *
	 * @param user   The {@link User} object to save.
	 * @param result The result of binding the form data to the User object.
	 * @param model  The model object used to pass data to the view.
	 * @return The redirect URL to the User list or the add form in case of
	 *         validation errors.
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
	 * Displays the form for updating an existing User.
	 *
	 * @param id    The ID of the User to update.
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the update form for the User.
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.getById(id);

		user.setPassword("");
		model.addAttribute("user", user);
		return USER_UPDATE;
	}

	/**
	 * Updates an existing User after validation.
	 *
	 * @param id     The ID of the User to update.
	 * @param user   The {@link User} object containing the updated data.
	 * @param result The result of binding the form data to the User object.
	 * @param model  The model object used to pass data to the view.
	 * @return The redirect URL to the list of User or the update form in case of
	 *         validation errors.
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
	 * Deletes a User by its ID.
	 *
	 * @param id    The ID of the User to delete.
	 * @param model The model object used to pass data to the view.
	 * @return The redirect URL to the list of remaining User.
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		userService.deleteById(id);
		model.addAttribute(USERS, userService.getAll());
		return REDIRECT_USER_LIST;
	}
}
