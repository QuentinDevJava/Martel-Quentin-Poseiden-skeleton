package com.nnk.springboot.controllers;

import static com.nnk.springboot.constants.AppConstants.BIDLISTS;
import static com.nnk.springboot.constants.AppConstants.BIDLISTS_ADD;
import static com.nnk.springboot.constants.AppConstants.BIDLISTS_UPDATE;
import static com.nnk.springboot.constants.AppConstants.REDIRECT_BIDLISTS;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class BidListController.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class BidListController {

	/** The bid service. */
	private final BidService bidService;

	/** The http servlet request. */
	private final HttpServletRequest httpServletRequest;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/bidList/list")
	public String home(Model model) {
		List<BidList> bidLists = bidService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute(BIDLISTS, bidLists);
		model.addAttribute("username", userConnect.getName());

		return "bidList/list";
	}

	/**
	 * Adds the bid form.
	 *
	 * @param bid the bid
	 * @return the string
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return BIDLISTS_ADD;
	}

	/**
	 * Validate.
	 *
	 * @param bid    the bid
	 * @param result the result
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		if (result.hasErrors()) {
			log.warn("bidList formulaire erreur");
			return BIDLISTS_ADD;
		}
		bidService.save(bid);
		return REDIRECT_BIDLISTS;
	}

	/**
	 * Show update form.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidService.getById(id);
		model.addAttribute("bidList", bidList);
		return BIDLISTS_UPDATE;
	}

	/**
	 * Update bid.
	 *
	 * @param id      the id
	 * @param bidList the bid list
	 * @param result  the result
	 * @param model   the model
	 * @return the string
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		if (result.hasErrors()) {
			log.warn("bidList formulaire erreur");
			return BIDLISTS_UPDATE;
		}
		bidList.setBidListId(id);
		bidService.save(bidList);
		List<BidList> bidLists = bidService.getAll();
		model.addAttribute(BIDLISTS, bidLists);
		return REDIRECT_BIDLISTS;
	}

	/**
	 * Delete bid.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		bidService.deleteById(id);
		List<BidList> bidLists = bidService.getAll();
		model.addAttribute(BIDLISTS, bidLists);
		return REDIRECT_BIDLISTS;
	}
}
