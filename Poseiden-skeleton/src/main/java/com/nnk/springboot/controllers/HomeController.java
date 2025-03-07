package com.nnk.springboot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nnk.springboot.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for handling home page.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final UserService userService;

	/**
	 * Handles requests to the home page ("/" or "").
	 * 
	 * @param userConnect the authentication object representing the current user.
	 * @param model       the model object that holds attributes to be passed to the
	 *                    view.
	 * @return the name of the view displaying ("home").
	 */
	@GetMapping({ "/", "" })
	public String home(Authentication userConnect, Model model) {
		boolean isAdmin = userService.isAdmin(userConnect);
		model.addAttribute("adminRole", isAdmin);
		model.addAttribute("username", userConnect.getName());
		return "home";
	}
}
