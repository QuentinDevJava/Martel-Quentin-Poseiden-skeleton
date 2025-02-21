package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * The Class TradeService.
 */
@RequiredArgsConstructor
@Service
public class TradeService {

	/** The trade repository. */
	private final TradeRepository tradeRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<Trade> getAll() {
		return tradeRepository.findAll();
	}

	/**
	 * Save.
	 *
	 * @param trade the trade
	 */
	public void save(@Valid Trade trade) {
		tradeRepository.save(trade);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Trade getById(Integer id) {
		return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Integer id) {
		tradeRepository.deleteById(id);
	}

	/**
	 * Gets the by account.
	 *
	 * @param account the account
	 * @return the by account
	 */
	public Trade getByAccount(String account) {
		return tradeRepository.findByAccount(account);
	}

}
