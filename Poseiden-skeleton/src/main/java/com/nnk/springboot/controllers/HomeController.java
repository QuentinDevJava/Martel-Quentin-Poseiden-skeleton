package com.nnk.springboot.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nnk.springboot.service.UserService;

/**
 * The Class HomeController.
 */
@Controller
public class HomeController {

	private final UserService userService;

	private String userInfo;

	HomeController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Home.
	 *
	 * @param userConnect the user connect
	 * @param model       the model
	 * @return the string
	 */
	@GetMapping({ "/", "" })
	public String home(Authentication userConnect, Model model) {

		if (userConnect instanceof UsernamePasswordAuthenticationToken) {
			userInfo = userService.getUsernameLoginInfo(userConnect);
			model.addAttribute("adminRole", userService.isAdmin(userInfo));

		} else if (userConnect instanceof OAuth2AuthenticationToken) {
			userInfo = userService.getOauth2LoginInfo(userConnect);
			model.addAttribute("adminRole", userService.isAdmin(userInfo));
		}
		model.addAttribute("username", userInfo);
		return "home";
	}

	/**
	 * Admin home.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/bidList/list";
	}

}
