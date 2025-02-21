package com.nnk.springboot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.service.UserService;

/**
 * The Class LoginController.
 */

@Controller
public class LoginController {

	/** The user repository. */
	private final UserService userService;

	LoginController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Login.
	 *
	 * @return the model and view
	 */
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	/**
	 * Gets the all user articles.
	 *
	 * @return the all user articles
	 */
	@GetMapping("/secure/article-details")
	public ModelAndView getAllUserArticles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userService.findAll());
		mav.setViewName("user/list");
		return mav;
	}

	/**
	 * Error.
	 *
	 * @return the model and view
	 */
	@GetMapping("/error403")
	public ModelAndView error(Authentication userConnect) {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.addObject("username", userConnect.getName());
		mav.setViewName("error/403");
		return mav;
	}
}
