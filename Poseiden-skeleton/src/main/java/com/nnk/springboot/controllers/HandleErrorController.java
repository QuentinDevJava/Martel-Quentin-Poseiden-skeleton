package com.nnk.springboot.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller handling error pages for the application. This controller is
 * responsible for processing and displaying different error pages (404, 500,
 * 403) with appropriate messages and authentication information.
 *
 */
@Controller
public class HandleErrorController implements ErrorController {

	/**
	 * Handles error requests for the application. This method is called when Spring
	 * detects an error and redirects to this route. It determines the error type
	 * and returns the appropriate page with corresponding messages.
	 *
	 * @param userConnect The authentication object containing connected user
	 *                    information
	 * @param request     The HttpServletRequest object containing request
	 *                    information
	 * @param model       The Spring MVC model for adding attributes to the view
	 * @return The name of the view to display (error/error).
	 * 
	 */
	@GetMapping("/error")
	public String handleError(Authentication userConnect, HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String errorMessage;
		String errorTitle;

		switch (status.toString()) {
		case "404":
			errorMessage = "The requested page could not be found.";
			errorTitle = "Page Not Found Exception";
			model.addAttribute("errorMsg", errorMessage);
			model.addAttribute("errorTitle", errorTitle);
			model.addAttribute("username", userConnect.getName());
			return "error/error";

		case "500":
			errorMessage = "An internal server error has occurred.";
			errorTitle = "Internal Server Error";
			model.addAttribute("errorMsg", errorMessage);
			model.addAttribute("errorTitle", errorTitle);
			model.addAttribute("username", userConnect.getName());
			return "error/error";

		case "403":
			errorMessage = "You are not authorized to access the requested data.";
			errorTitle = "Access Denied Exception";
			model.addAttribute("errorMsg", errorMessage);
			model.addAttribute("errorTitle", errorTitle);
			model.addAttribute("username", userConnect.getName());
			return "error/error";

		default:
			errorMessage = "An unexpected error has occurred.";
			errorTitle = "Error Occurred";
			model.addAttribute("errorMsg", errorMessage);
			model.addAttribute("errorTitle", errorTitle);
			model.addAttribute("username", userConnect.getName());
			return "error/error";
		}
	}
}