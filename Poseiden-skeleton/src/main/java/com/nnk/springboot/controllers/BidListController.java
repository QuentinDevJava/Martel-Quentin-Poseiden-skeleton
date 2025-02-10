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

@Slf4j
@Controller
public class BidListController {
	// TODO: Inject Bid service

	@Autowired
	private BidService bidService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@GetMapping("/bidList/list")
	public String home(Model model) {
		List<BidList> bidLists = bidService.getAll();
		Principal userConnect = httpServletRequest.getUserPrincipal();

		model.addAttribute("bidLists", bidLists);
		model.addAttribute("username", userConnect.getName());

		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return bid list

		if (result.hasErrors()) {
			log.warn("bidList formulaire erreur");
		}

		bidService.save(bid);

		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Bid by Id and to model then show to the form

		BidList bidList = bidService.getById(id);
		if (bidList == null) {
			log.warn("bidList est vide ou null");
		}
		model.addAttribute("bidList", bidList);

		return "bidList/update";
	}

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

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Bid by Id and delete the bid, return to Bid list

		bidService.deleteById(id);
		List<BidList> bidLists = bidService.getAll();
		model.addAttribute("bidLists", bidLists);
		return "redirect:/bidList/list";
	}
}
