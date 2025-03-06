package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The {@link LoginController} manages the login page and user list.
 * 
 * <p>
 * Note: The custom login page is available at "/login", but the current Spring
 * Security configuration uses the default page. To use the custom page,
 * configure Spring Security with {@code formLogin().loginPage("/login")}.
 * </p>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

	/** The user repository. */
	private final UserService userService;

	/**
	 * Displays the list of authenticated users. This method requires valid
	 * authentication and users with the "ADMIN" role.
	 * 
	 * @return A ModelAndView containing the "user/list" view
	 */
	@GetMapping("/secure/article-details")
	public ModelAndView getAllUserArticles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userService.getAll());
		mav.setViewName("user/list");
		return mav;
	}
}


// TODO supprimer ce bout de code si non utilise
/**
 * Displays the login view for authentication. To use the custom page, configure
 * Spring Security with {@code formLogin().loginPage("/login")}.
 * 
 * @return A ModelAndView initialized with the "login" view
 */
//@GetMapping("/login")
//public ModelAndView login() {
//	ModelAndView mav = new ModelAndView();
//	mav.setViewName("login");
//	return mav;
//}