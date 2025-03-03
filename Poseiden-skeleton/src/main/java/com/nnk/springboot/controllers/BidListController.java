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
 * Controller responsible for managing BidList entities. Allows displaying,
 * adding, updating, and deleting BidList.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class BidListController {

	/** The bid service. */
	private final BidService bidService;

	/** The HTTP request used to retrieve the authenticated user's information. */
	private final HttpServletRequest httpServletRequest;

	/**
	 * Displays the list of all BidList.
	 *
	 * @param model The model object used to pass data to the view.
	 * @return The name of the view displaying the list of bid.
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
	 * Displays the form for adding a new BidList.
	 *
	 * @param bid The empty {@link BidList} object to bind to the form.
	 * @return The name of the view displaying the add BidList form.
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return BIDLISTS_ADD;
	}

	/**
	 * Validates and saves the new BidList if there are no errors.
	 *
	 * @param bid    The {@link BidList} to save.
	 * @param result The result of binding the form data to the BidList object.
	 * @return The redirect URL to the list of BidList or the add form if errors
	 *         occur.
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("Error in BidList form submission.");
			return BIDLISTS_ADD;
		}

		bidService.save(bid);
		return REDIRECT_BIDLISTS;
	}

	/**
	 * Displays the form for updating an existing BidList.
	 *
	 * @param id The ID of the BidList to update.
	 * @return The name of the view displaying the update BidList form.
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidService.getById(id);
		model.addAttribute("bidList", bidList);
		return BIDLISTS_UPDATE;
	}

	/**
	 * Updates an existing BidList after validation.
	 *
	 * @param id      The ID of the BidList to update.
	 * @param bidList The {@link BidList} object containing updated information.
	 * @param result  The result of binding the form data to the BidList object.
	 * @return The redirect URL to the list of BidList or the update form if errors
	 *         occur.
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result) {

		if (result.hasErrors()) {
			log.warn("Error in BidList form submission.");
			return BIDLISTS_UPDATE;
		}

		bidList.setBidListId(id);
		bidService.save(bidList);
		return REDIRECT_BIDLISTS;
	}

	/**
	 * Deletes a BidList by its ID.
	 *
	 * @param id The ID of the BidList to delete.
	 * @return The redirect URL to the list of remaining BidList.
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id) {
		bidService.deleteById(id);
		return REDIRECT_BIDLISTS;
	}
}
