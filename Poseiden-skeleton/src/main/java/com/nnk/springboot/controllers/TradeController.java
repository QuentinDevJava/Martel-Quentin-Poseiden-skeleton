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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class TradeController.
 */
@Slf4j
@Controller
public class TradeController {

	/** The trade service. */
	@Autowired
	private TradeService tradeService;

	/** The http servlet request. */
	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/trade/list")
	public String home(Model model) {

		List<Trade> trades = tradeService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute("trades", trades);
		model.addAttribute("username", userConnect.getName());

		return "trade/list";
	}

	/**
	 * Adds the user.
	 *
	 * @param bid the bid
	 * @return the string
	 */
	@GetMapping("/trade/add")
	public String addUser(Trade bid) {
		return "trade/add";
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
			log.warn("bidList formulaire erreur");
			return "trade/add";
		}
		tradeService.save(trade);
		return "redirect:/trade/list";
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
		return "trade/update";
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
			log.warn("bidList formulaire erreur");
			return "trade/update";
		}

		trade.setTradeId(id);
		tradeService.save(trade);
		List<Trade> trades = tradeService.getAll();
		model.addAttribute("trades", trades);

		return "redirect:/trade/list";
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
		// TODO: Find Trade by Id and delete the Trade, return to Trade list
//		Trade trade = tradeService.getById(id);
//		if (trade == null) {
//		log.warn("bidList est vide ou null");
//	}
		tradeService.deleteById(id);
		List<Trade> trades = tradeService.getAll();
		model.addAttribute("trades", trades);
		return "redirect:/trade/list";
	}
}
