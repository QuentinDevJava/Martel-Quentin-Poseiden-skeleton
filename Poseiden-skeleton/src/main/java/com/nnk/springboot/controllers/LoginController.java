package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
//TODO utilité de cette class c'est spring security qui gere cette partie ?
@Controller
@RequestMapping("app")
public class LoginController {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

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
		mav.addObject("users", userRepository.findAll());
		mav.setViewName("user/list");
		return mav;
	}

	/**
	 * Error.
	 *
	 * @return the model and view
	 */
	@GetMapping("/error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}
}
