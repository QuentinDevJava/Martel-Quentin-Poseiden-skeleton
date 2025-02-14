package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.RuleName;

// TODO: Auto-generated Javadoc
/**
 * The Interface RuleNameRepository.
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the rule name
	 */
	RuleName findByName(String name);
}
