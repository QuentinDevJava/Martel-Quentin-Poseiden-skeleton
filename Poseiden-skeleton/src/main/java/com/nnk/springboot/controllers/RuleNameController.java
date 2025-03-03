package com.nnk.springboot.controllers;

import static com.nnk.springboot.constants.AppConstants.REDIRECT_RULENAME_LIST;
import static com.nnk.springboot.constants.AppConstants.RULENAMES;
import static com.nnk.springboot.constants.AppConstants.RULENAME_ADD;
import static com.nnk.springboot.constants.AppConstants.RULENAME_UPDATE;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for managing RuleName entities. Allows displaying,
 * adding, updating, and deleting RuleName.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class RuleNameController {

	/** The rule name service. */
	private final RuleNameService ruleNameService;

	/**
	 * The HTTP servlet request used to retrieve the authenticated user's
	 * information.
	 */

	private final HttpServletRequest httpServletRequest;

	/**
	 * Displays the list of all RuleName.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of RuleName.
	 */
	@GetMapping("/ruleName/list")
	public String home(Model model) {

		List<RuleName> ruleNames = ruleNameService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute(RULENAMES, ruleNames);
		model.addAttribute("username", userConnect.getName());

		return "ruleName/list";
	}

	/**
	 * Displays the form for adding a new RuleName.
	 *
	 * @param ruleName The empty {@link RuleName} object to bind to the form.
	 * @return The name of the view displaying the add RuleName form.
	 */
	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName ruleName) {
		return RULENAME_ADD;
	}

	/**
	 * Validates and saves a new RuleName if no errors are present.
	 *
	 * @param ruleName The {@link RuleName} object to save.
	 * @param result   The result of binding the form data to the RuleName object.
	 * @return The redirect URL to the RuleName list or the add form in case of
	 *         validation errors.
	 */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("ruleName formulaire erreur");
			return RULENAME_ADD;
		}
		ruleNameService.save(ruleName);
		return REDIRECT_RULENAME_LIST;
	}

	/**
	 * Displays the form for updating an existing RuleName.
	 *
	 * @param id    The ID of the RuleName to update.
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the update form for the RuleName.
	 */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameService.getById(id);
		model.addAttribute("ruleName", ruleName);
		return RULENAME_UPDATE;
	}

	/**
	 * Updates an existing RuleName after validation.
	 *
	 * @param id       The ID of the RuleName to update.
	 * @param ruleName The {@link RuleName} object containing the updated data.
	 * @param result   The result of binding the form data to the RuleName object.
	 * @return The redirect URL to the list of RuleName or the update form in case
	 *         of validation errors.
	 */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("ruleName formulaire erreur");
			return RULENAME_UPDATE;
		}

		ruleName.setId(id);
		ruleNameService.save(ruleName);
		return REDIRECT_RULENAME_LIST;
	}

	/**
	 * Deletes a RuleName by its ID.
	 *
	 * @param id The ID of the RuleName to delete.
	 * @return The redirect URL to the list of remaining RuleName.
	 */
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id) {
		ruleNameService.deleteById(id);
		return REDIRECT_RULENAME_LIST;
	}
}
