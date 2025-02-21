package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.RuleName;

/**
 * The Interface RuleNameRepository.
 */
@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the rule name
	 */
	RuleName findByName(String name);
}
