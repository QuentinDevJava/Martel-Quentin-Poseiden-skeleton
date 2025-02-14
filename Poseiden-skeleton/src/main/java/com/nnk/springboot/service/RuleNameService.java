package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class RuleNameService.
 */
@NoArgsConstructor
@Service
public class RuleNameService {

	/** The rule name repository. */
	@Autowired
	private RuleNameRepository ruleNameRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<RuleName> getAll() {
		return ruleNameRepository.findAll();
	}

	/**
	 * Save.
	 *
	 * @param ruleName the rule name
	 */
	public void save(RuleName ruleName) {
		ruleNameRepository.save(ruleName);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public RuleName getById(Integer id) {
		return ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Integer id) {
		ruleNameRepository.deleteById(id);
	}

	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	public RuleName getByName(String name) {
		return ruleNameRepository.findByName(name);
	}

}
