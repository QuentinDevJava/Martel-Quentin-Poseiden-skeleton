package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Trade;

// TODO: Auto-generated Javadoc
/**
 * The Interface TradeRepository.
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {

	/**
	 * Find by account.
	 *
	 * @param account the account
	 * @return the trade
	 */
	Trade findByAccount(String account);
}
