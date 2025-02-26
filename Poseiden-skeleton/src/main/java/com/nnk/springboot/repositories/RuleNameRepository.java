package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.RuleName;

/**
 * The Interface RuleNameRepository.
 * 
 * <p>
 * Repository interface for accessing {@link RuleName} entities from the
 * database. This interface extends {@link JpaRepository} for basic CRUD
 * operations.
 * </p>
 */
@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the {@link RuleName} or null no rule name is found
	 */
	RuleName findByName(String name);
}
