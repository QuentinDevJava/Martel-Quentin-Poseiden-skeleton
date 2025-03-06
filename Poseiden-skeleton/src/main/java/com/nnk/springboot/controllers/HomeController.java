package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

	private String userInfo;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	// TODO cette logique m'a echappe pendant la revue mais pourrait etre améliorer car bcp de if/else. Prete pas attention cependant.
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

		if (userConnect instanceof UsernamePasswordAuthenticationToken) {
			userInfo = userService.getUsernameLoginInfo(userConnect);
			if ("local".equals(activeProfile)) {
				model.addAttribute("adminRole", userInfo.equals("admin"));
			} else {
				model.addAttribute("adminRole", userService.isAdmin(userInfo));
			}
		} else if (userConnect instanceof OAuth2AuthenticationToken) {
			userInfo = userService.getOauth2LoginInfo(userConnect);
			model.addAttribute("adminRole", userService.isAdmin(userInfo));
		}
		model.addAttribute("username", userInfo);
		return "home";
	}

}
