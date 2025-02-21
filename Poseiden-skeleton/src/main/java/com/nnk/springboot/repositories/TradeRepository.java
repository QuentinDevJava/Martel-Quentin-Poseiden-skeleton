package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Trade;

/**
 * The Interface TradeRepository.
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

	/**
	 * Find by account.
	 *
	 * @param account the account
	 * @return the trade
	 */
	Trade findByAccount(String account);
}
