package com.nnk.springboot.controllers;

/**
 * Controller handling error pages for the application. This controller is
 * responsible for processing and displaying different error pages (404, 500,
 * 403) with appropriate messages and authentication information.
 *
 */
//@Controller
public class HandleErrorController {

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
	// @GetMapping("/error")
	// public String handleError(HttpServletRequest request, Model model) {

	// int statusCode = (Integer)
	// request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
//
//		model.addAttribute(ERROR_MSG, message);
//		model.addAttribute(ERROR_TITLE, "");
//		model.addAttribute(USERNAME, "");
//
//		return "error/error";

//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		String errorMessage;
//		String errorTitle;
//
//		switch (status.toString()) {
//		case "404":
//			errorMessage = "The requested page could not be found.";
//			errorTitle = "Page Not Found Exception";
//			break;
//
//		case "500":
//			errorMessage = "An internal server error has occurred.";
//			errorTitle = "Internal Server Error";
//			break;
//
//		case "403":
//			errorMessage = "You are not authorized to access the requested data.";
//			errorTitle = "Access Denied Exception";
//			break;
//
//		default:
//			errorMessage = "An unexpected error has occurred.";
//			errorTitle = "Error Occurred";
//			break;
//		}
//		model.addAttribute(ERROR_MSG, errorMessage);
//		model.addAttribute(ERROR_TITLE, errorTitle);
//		model.addAttribute(USERNAME, userConnect.getName());

//	}
}