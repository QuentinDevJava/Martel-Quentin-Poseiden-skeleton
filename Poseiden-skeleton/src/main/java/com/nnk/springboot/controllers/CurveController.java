package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class CurveController.
 */
@Slf4j
@Controller
public class CurveController {

	/** The curve service. */
	@Autowired
	private CurveService curveService;

	/** The http servlet request. */
	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/curvePoint/list")
	public String home(Model model) {

		List<CurvePoint> curvePoints = curveService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute("curvePoints", curvePoints);
		model.addAttribute("username", userConnect.getName());

		return "curvePoint/list";
	}

	/**
	 * Adds the curve point form.
	 *
	 * @param bid the bid
	 * @return the string
	 */
	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	/**
	 * Validate.
	 *
	 * @param curvePoint the curve point
	 * @param result     the result
	 * @param model      the model
	 * @return the string
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.warn("curvePoint formulaire erreur");
			return "curvePoint/add";
		}
		curveService.save(curvePoint);
		return "redirect:/curvePoint/list";
	}

	/**
	 * Show update form.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curveService.getById(id);
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	/**
	 * Update curve point.
	 *
	 * @param id         the id
	 * @param curvePoint the curve point
	 * @param result     the result
	 * @param model      the model
	 * @return the string
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			log.warn("curvepoint formulaire erreur");
			return "curvePoint/update";

		}
		curvePoint.setId(id);
		curveService.save(curvePoint);
		List<CurvePoint> curvePoints = curveService.getAll();
		model.addAttribute("curvePoints", curvePoints);
		return "redirect:/curvePoint/list";
	}

	/**
	 * Delete curve point.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Curve by Id and delete the Curve, return to Curve list
//		CurvePoint curvePoint = curveService.getById(id);
//		if (curvePoint == null) {
//			log.warn("curvePoint est vide ou null");
//		}
		curveService.deleteById(id);
		List<CurvePoint> curvePoints = curveService.getAll();
		model.addAttribute("curvePoints", curvePoints);
		return "redirect:/curvePoint/list";
	}
}
