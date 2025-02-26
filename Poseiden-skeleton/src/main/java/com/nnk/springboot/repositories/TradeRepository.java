package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Trade;

/**
 * The Interface TradeRepository.
 * 
 * <p>
 * Repository interface for accessing {@link Trade} entities from the database.
 * This interface extends {@link JpaRepository} for basic CRUD operations.
 * </p>
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

	/**
	 * Find by account.
	 *
	 * @param account the account
	 * @return the {@link Trade} or null no trade is found
	 */
	Trade findByAccount(String account);
}
