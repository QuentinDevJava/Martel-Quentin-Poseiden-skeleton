package com.nnk.springboot.controllers;

import static com.nnk.springboot.constants.AppConstants.REDIRECT_TRADE_LIST;
import static com.nnk.springboot.constants.AppConstants.TRADES;
import static com.nnk.springboot.constants.AppConstants.TRADE_ADD;
import static com.nnk.springboot.constants.AppConstants.TRADE_UPDATE;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for managing Trade entities. Allows displaying,
 * adding, updating, and deleting Trade.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class TradeController {

	/** The trade service. */
	private final TradeService tradeService;

	/**
	 * The HTTP servlet request used to retrieve the authenticated user's
	 * information.
	 */
	private final HttpServletRequest httpServletRequest;

	/**
	 * Displays the list of all Trade.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of Trade.
	 */
	@GetMapping("/trade/list")
	public String homeTrade(Model model) {

		List<Trade> trades = tradeService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute(TRADES, trades);
		model.addAttribute("username", userConnect.getName());

		return "trade/list";
	}

	/**
	 * Displays the form for adding a new Trade.
	 *
	 * @param trade The empty {@link Trade} object to bind to the form.
	 * @return The name of the view displaying the add Trade form.
	 */
	@GetMapping("/trade/add")
	public String addTrade(Trade trade) {
		return TRADE_ADD;
	}

	/**
	 * Validates and saves a new Trade if no errors are present.
	 *
	 * @param trade  The {@link Trade} object to save.
	 * @param result The result of binding the form data to the Trade object.
	 * @return The redirect URL to the Trade list or the add form in case of
	 *         validation errors.
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("Trade formulaire erreur");
			return TRADE_ADD;
		}

		tradeService.save(trade);
		return REDIRECT_TRADE_LIST;
	}

	/**
	 * Displays the form for updating an existing Trade.
	 *
	 * @param id    The ID of the Trade to update.
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the update form for the Trade.
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeService.getById(id);
		model.addAttribute("trade", trade);
		return TRADE_UPDATE;
	}

	/**
	 * Updates an existing Trade after validation.
	 *
	 * @param id     The ID of the Trade to update.
	 * @param trade  The {@link Trade} object containing the updated data.
	 * @param result The result of binding the form data to the Trade object.
	 * @return The redirect URL to the list of Trade or the update form in case of
	 *         validation errors.
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("Trade formulaire erreur");
			return TRADE_UPDATE;
		}

		trade.setTradeId(id);
		tradeService.save(trade);
		return REDIRECT_TRADE_LIST;
	}

	/**
	 * Deletes a Trade by its ID.
	 *
	 * @param id The ID of the Trade to delete.
	 * @return The redirect URL to the list of remaining Trade.
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		tradeService.deleteById(id);
		return REDIRECT_TRADE_LIST;
	}
}
