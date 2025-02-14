package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.BidList;

/**
 * The Interface BidListRepository.
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

	/**
	 * Find by account.
	 *
	 * @param account the account
	 * @return the bid list
	 */
	BidList findByAccount(String account);

}
