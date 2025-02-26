package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.BidList;

/**
 * The Interface BidListRepository.
 * 
 * <p>
 * Repository interface for accessing {@link BidList} entities from the
 * database. This interface extends {@link JpaRepository} for basic CRUD
 * operations.
 * </p>
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

	/**
	 * Find by account.
	 *
	 * @param account the account
	 * @return the {@link BidList} or null no bidlist is found
	 */
	BidList findByAccount(String account);

}
