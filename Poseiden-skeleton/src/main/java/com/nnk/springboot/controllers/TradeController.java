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
	 * Displays the list of all Trade.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of Trade.
	 */
	@GetMapping("/trade/add")
	public String addTrade(Trade trade) {
		return TRADE_ADD;
	}

	/**
	 * Validate.
	 *
	 * @param trade  the trade
	 * @param result the result
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.warn("Trade formulaire erreur");
			return TRADE_ADD;
		}
		tradeService.save(trade);
		return REDIRECT_TRADE_LIST;
	}

	/**
	 * Show update form.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeService.getById(id);
		model.addAttribute("trade", trade);
		return TRADE_UPDATE;
	}

	/**
	 * Update trade.
	 *
	 * @param id     the id
	 * @param trade  the trade
	 * @param result the result
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.warn("Trade formulaire erreur");
			return TRADE_UPDATE;
		}

		trade.setTradeId(id);
		tradeService.save(trade);
		List<Trade> trades = tradeService.getAll();
		model.addAttribute(TRADES, trades);

		return REDIRECT_TRADE_LIST;
	}

	/**
	 * Delete trade.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		tradeService.deleteById(id);
		List<Trade> trades = tradeService.getAll();
		model.addAttribute(TRADES, trades);
		return REDIRECT_TRADE_LIST;
	}
}
