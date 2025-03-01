package com.nnk.springboot.controllers;

import static com.nnk.springboot.constants.AppConstants.CURVEPOINTS;
import static com.nnk.springboot.constants.AppConstants.CURVEPOINT_ADD;
import static com.nnk.springboot.constants.AppConstants.CURVEPOINT_UPDATE;
import static com.nnk.springboot.constants.AppConstants.REDIRECT_CURVEPOINT_LIST;

import java.security.Principal;
import java.util.List;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for managing CurvePoint entities. Allows displaying,
 * adding, updating, and deleting CurvePoint.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class CurveController {

	/** The curve service. */
	private final CurveService curveService;

	/**
	 * The HTTP servlet request used to retrieve the authenticated user's
	 * information.
	 */
	private final HttpServletRequest httpServletRequest;

	/**
	 * Displays the list of all CurvePoints.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of CurvePoint.
	 */
	@GetMapping("/curvePoint/list")
	public String home(Model model) {

		List<CurvePoint> curvePoints = curveService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute(CURVEPOINTS, curvePoints);
		model.addAttribute("username", userConnect.getName());

		return "curvePoint/list";
	}

	/**
	 * Displays the form for adding a new CurvePoint.
	 *
	 * @param curvePoint The empty {@link CurvePoint} object to bind to the form.
	 * @return The name of the view displaying the add CurvePoint form.
	 */
	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePoint curvePoint) {
		return CURVEPOINT_ADD;
	}

	/**
	 * Validates and saves a new CurvePoint if no errors are present.
	 *
	 * @param curvePoint The {@link CurvePoint} object to save.
	 * @param result     The result of binding the form data to the CurvePoint
	 *                   object.
	 * @param model      The model object used to pass data to the view.
	 * @return The redirect URL to the CurvePoint list or the add form in case of
	 *         validation errors.
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.warn("CurvePoint form error.");
			return CURVEPOINT_ADD;
		}
		curveService.save(curvePoint);
		return REDIRECT_CURVEPOINT_LIST;
	}

	/**
	 * Displays the form for updating an existing CurvePoint.
	 *
	 * @param id    The ID of the CurvePoint to update.
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the update form for the CurvePoint.
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curveService.getById(id);
		model.addAttribute("curvePoint", curvePoint);
		return CURVEPOINT_UPDATE;
	}

	/**
	 * Updates an existing CurvePoint after validation.
	 *
	 * @param id         The ID of the CurvePoint to update.
	 * @param curvePoint The {@link CurvePoint} object containing the updated data.
	 * @param result     The result of binding the form data to the CurvePoint
	 *                   object.
	 * @param model      The model object used to pass data to the view.
	 * @return The redirect URL to the list of CurvePoint or the update form in case
	 *         of validation errors.
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			log.warn("CurvePoint form error.");
			return CURVEPOINT_UPDATE;
		}
		curvePoint.setId(id);
		curveService.save(curvePoint);
		List<CurvePoint> curvePoints = curveService.getAll();
		model.addAttribute(CURVEPOINTS, curvePoints);
		return REDIRECT_CURVEPOINT_LIST;
	}

	/**
	 * Deletes a CurvePoint by its ID.
	 *
	 * @param id    The ID of the CurvePoint to delete.
	 * @param model The model object used to pass data to the view.
	 * @return The redirect URL to the list of remaining CurvePoints.
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
		curveService.deleteById(id);
		List<CurvePoint> curvePoints = curveService.getAll();
		model.addAttribute(CURVEPOINTS, curvePoints);
		return REDIRECT_CURVEPOINT_LIST;
	}
}
