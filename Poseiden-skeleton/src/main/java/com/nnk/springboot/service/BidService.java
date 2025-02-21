package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class BidService.
 */
@RequiredArgsConstructor
@Service
public class BidService {

	/** The bid list repository. */
	private final BidListRepository bidListRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<BidList> getAll() {
		return bidListRepository.findAll();
	}

	/**
	 * Save.
	 *
	 * @param bid the bid
	 */
	public void save(BidList bid) {
		bidListRepository.save(bid);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public BidList getById(Integer id) {
		return bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Integer id) {
		bidListRepository.deleteById(id);
	}

	/**
	 * Gets the by account.
	 *
	 * @param account the account
	 * @return the by account
	 */
	public BidList getByAccount(String account) {
		return bidListRepository.findByAccount(account);
	}

}
