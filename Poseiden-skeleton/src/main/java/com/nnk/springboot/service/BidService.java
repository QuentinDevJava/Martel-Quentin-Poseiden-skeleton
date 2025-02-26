package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class BidService.
 *
 * <p>
 * Service class responsible for managing {@link BidList} entities.
 * </p>
 * 
 * <p>
 * <b>Key Methods:</b>
 * </p>
 * <ul>
 * <li>{@link #getAll()} - Retrieves all bid lists from the repository.</li>
 * <li>{@link #save(BidList)} - Saves a new or existing bid list to the
 * repository.</li>
 * <li>{@link #getById(Integer)} - Retrieves a bid list by its ID.</li>
 * <li>{@link #deleteById(Integer)} - Deletes a bid list by its ID.</li>
 * <li>{@link #getByAccount(String)} - Retrieves a bid list by its account
 * name.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Service
public class BidService {

	/** The bid list repository. */
	private final BidListRepository bidListRepository;

	/**
	 * Gets all {@link BidList}.
	 *
	 * @return the list of all {@link BidList}
	 */
	public List<BidList> getAll() {
		return bidListRepository.findAll();
	}

	/**
	 * Save a {@link BidList}.
	 *
	 * @param bid the {@link BidList} object to be saved
	 */
	public void save(BidList bid) {
		bidListRepository.save(bid);
	}

	/**
	 * Gets the {@link BidList} by id.
	 *
	 * @param id the id
	 * @return the {@link BidList} object with the specified ID
	 * @throws IllegalArgumentException if no bid list with the given ID is found
	 */
	public BidList getById(Integer id) {
		return bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
	}

	/**
	 * Delete {@link BidList} by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Integer id) {
		bidListRepository.deleteById(id);
	}

	/**
	 * Gets the {@link BidList} by account.
	 *
	 * @param account the account
	 * @return the {@link BidList} object for the given account, or {@code null} if
	 *         not found
	 */
	public BidList getByAccount(String account) {
		return bidListRepository.findByAccount(account);
	}

}
