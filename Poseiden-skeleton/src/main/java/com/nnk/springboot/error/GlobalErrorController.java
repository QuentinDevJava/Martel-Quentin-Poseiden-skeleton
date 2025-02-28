package com.nnk.springboot.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorController {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		System.out.println("ok");

		model.addAttribute("errorMsg", "test");

		return "error";
	}

}
