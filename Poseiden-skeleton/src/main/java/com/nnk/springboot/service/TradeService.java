package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class TradeService.
 *
 * <p>
 * Service class responsible for managing {@link Trade} entities.
 * </p>
 * 
 * <p>
 * <b>Key Methods:</b>
 * </p>
 * <ul>
 * <li>{@link #getAll()} - Retrieves all trades from the repository.</li>
 * <li>{@link #save(Trade)} - Saves a new or existing trade to the
 * repository.</li>
 * <li>{@link #getById(Integer)} - Retrieves a trade by its ID.</li>
 * <li>{@link #deleteById(Integer)} - Deletes a trade by its ID.</li>
 * <li>{@link #getByAccount(String)} - Retrieves a trade by its account
 * name.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Service
public class TradeService {

	/** The trade repository. */
	private final TradeRepository tradeRepository;

	/**
	 * Gets all {@link Trade}.
	 * 
	 * @return the list of all {@link Trade} entities
	 */
	public List<Trade> getAll() {
		return tradeRepository.findAll();
	}

	/**
	 * Save a {@link Trade}.
	 * 
	 * @param trade the {@link Trade} object to be saved
	 */
	public void save(Trade trade) {
		tradeRepository.save(trade);
	}

	/**
	 * Gets the {@link Trade} by id.
	 *
	 * @param id the ID of the trade to retrieve
	 * @return the {@link Trade} object with the specified ID
	 * @throws IllegalArgumentException if no trade with the given ID is found
	 */
	public Trade getById(Integer id) {
		return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
	}

	/**
	 * Delete {@link Trade} by id.
	 * 
	 * @param id the ID of the trade to delete
	 */
	public void deleteById(Integer id) {
		tradeRepository.deleteById(id);
	}

	/**
	 * Gets the {@link Trade} by account.
	 *
	 * @param account the account
	 * @return the {@link Trade} object for the given account, or {@code null} if
	 *         not found
	 */
	public Trade getByAccount(String account) {
		return tradeRepository.findByAccount(account);
	}
}
