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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class BidListController.
 */
@Slf4j
@Controller
public class BidListController {
	// TODO: Inject Bid service

	/** The bid service. */
	@Autowired
	private BidService bidService;

	/** The http servlet request. */
	@Autowired
	private HttpServletRequest httpServletRequest;

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

		model.addAttribute("bidLists", bidLists);
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
		return "bidList/add";
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
		// TODO: check data valid and save to db, after saving return bid list

		if (result.hasErrors()) {
			log.warn("bidList formulaire erreur");
		}

		bidService.save(bid);

		return "bidList/add";
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
		// TODO: get Bid by Id and to model then show to the form

		BidList bidList = bidService.getById(id);
//		if (bidList == null) {
//			log.warn("bidList est vide ou null");
//		}
		model.addAttribute("bidList", bidList);

		return "bidList/update";
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
		// TODO: check required fields, if valid call service to update Bid and return
		// list Bid

		if (result.hasErrors()) {
			log.warn("bidList formulaire erreur");
		}

		bidList.setBidListId(id);
		bidService.save(bidList);
		List<BidList> bidLists = bidService.getAll();
		model.addAttribute("bidLists", bidLists);
		return "redirect:/bidList/list";
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
		// TODO: Find Bid by Id and delete the bid, return to Bid list
//		BidList bidList = bidService.getById(id);
//		if (bidList == null) {
//			log.warn("bidList est vide ou null");
//		}
		bidService.deleteById(id);
		List<BidList> bidLists = bidService.getAll();
		model.addAttribute("bidLists", bidLists);
		return "redirect:/bidList/list";
	}
}
