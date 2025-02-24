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
 * The Class RuleNameController.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class RuleNameController {

	/** The rule name service. */
	private final RuleNameService ruleNameService;

	/** The http servlet request. */
	private final HttpServletRequest httpServletRequest;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the string
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
	 * Adds the rule form.
	 *
	 * @param ruleName the rule name
	 * @return the string
	 */
	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName ruleName) {
		return RULENAME_ADD;
	}

	/**
	 * Validate.
	 *
	 * @param ruleName the rule name
	 * @param result   the result
	 * @param model    the model
	 * @return the string
	 */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.warn("ruleName formulaire erreur");
			return RULENAME_ADD;
		}
		ruleNameService.save(ruleName);
		return REDIRECT_RULENAME_LIST;
	}

	/**
	 * Show update form.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameService.getById(id);
		model.addAttribute("ruleName", ruleName);
		return RULENAME_UPDATE;
	}

	/**
	 * Update rule name.
	 *
	 * @param id       the id
	 * @param ruleName the rule name
	 * @param result   the result
	 * @param model    the model
	 * @return the string
	 */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			log.warn("bidList formulaire erreur");
			return RULENAME_UPDATE;
		}

		ruleName.setId(id);
		ruleNameService.save(ruleName);
		List<RuleName> ruleNames = ruleNameService.getAll();
		model.addAttribute("ruleName", ruleNames);

		return REDIRECT_RULENAME_LIST;
	}

	/**
	 * Delete rule name.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		ruleNameService.deleteById(id);
		List<RuleName> ruleNames = ruleNameService.getAll();
		model.addAttribute(RULENAMES, ruleNames);

		return REDIRECT_RULENAME_LIST;
	}
}
