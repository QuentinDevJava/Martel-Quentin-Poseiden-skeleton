package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nnk.springboot.service.UserService;

/**
 * The Class HomeController.
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	/**
	 * Home.
	 *
	 * @param userConnect the user connect
	 * @param model       the model
	 * @return the string
	 */
	@GetMapping({ "/*", "" })
	public String home(Principal userConnect, Model model) {

		StringBuilder userInfo = new StringBuilder();

		if (userConnect instanceof UsernamePasswordAuthenticationToken) {

			userInfo.append(getUsernameLoginInfo(userConnect));

			model.addAttribute("adminRole", userService.isAdmin(getUsernameLoginInfo(userConnect)));

		} else if (userConnect instanceof OAuth2AuthenticationToken) {
			userInfo.append(getOauth2LoginInfo(userConnect));
			model.addAttribute("adminRole", "true");

		}

		model.addAttribute("username", userInfo.toString());
		return "home";
	}

	/**
	 * Gets the username login info.
	 *
	 * @param userConnect the user connect
	 * @return the username login info
	 */
	private StringBuilder getUsernameLoginInfo(Principal userConnect) {
		StringBuilder userInfo = new StringBuilder();
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) userConnect;
		if (token.isAuthenticated()) {
			User userPrincipal = (User) token.getPrincipal();
			userInfo.append(userPrincipal.getUsername());

		} else {
			userInfo.append(userConnect.getName());
		}
		return userInfo;
	}

	/**
	 * Gets the oauth 2 login info.
	 *
	 * @param user the user
	 * @return the oauth 2 login info
	 */
	private StringBuilder getOauth2LoginInfo(Principal user) {
		StringBuilder protectedInfo = new StringBuilder();
		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		if (authToken.isAuthenticated()) {
			Map<String, Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
			if (userAttributes.get("Name") == null || userAttributes.get("Name") == "") {
				protectedInfo.append(" Login : " + userAttributes.get("login"));

			} else if (userAttributes.get("login") == null || userAttributes.get("Name") == "") {
				protectedInfo.append("Name and login info is empty or null");
			} else {
				protectedInfo.append("Name : " + userAttributes.get("name"));
				protectedInfo.append(" Login : " + userAttributes.get("login"));
			}

		} else {
			protectedInfo.append("NA");
		}
		return protectedInfo;
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
