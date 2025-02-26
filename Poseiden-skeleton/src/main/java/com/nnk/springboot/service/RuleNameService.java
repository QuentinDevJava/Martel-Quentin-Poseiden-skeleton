package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import lombok.RequiredArgsConstructor;

/**
 * The Class RuleNameService.
 *
 * <p>
 * Service class responsible for managing {@link RuleName} entities.
 * </p>
 * 
 * <p>
 * <b>Key Methods:</b>
 * </p>
 * <ul>
 * <li>{@link #getAll()} - Retrieves all rule names from the repository.</li>
 * <li>{@link #save(RuleName)} - Saves a new or existing rule name to the
 * repository.</li>
 * <li>{@link #getById(Integer)} - Retrieves a rule name by its ID.</li>
 * <li>{@link #deleteById(Integer)} - Deletes a rule name by its ID.</li>
 * <li>{@link #getByName(String)} - Retrieves a rule name by its name.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Service
public class RuleNameService {

	/** The rule name repository. */
	private final RuleNameRepository ruleNameRepository;

	/**
	 * Gets all {@link RuleName}.
	 *
	 * @return the list of all {@link RuleName} entities
	 */
	public List<RuleName> getAll() {
		return ruleNameRepository.findAll();
	}

	/**
	 * Save a {@link RuleName}.
	 *
	 * @param ruleName the {@link RuleName} object to be saved
	 */
	public void save(RuleName ruleName) {
		ruleNameRepository.save(ruleName);
	}

	/**
	 * Gets the {@link RuleName} by id.
	 *
	 * @param id the ID of the rule name to retrieve
	 * @return the {@link RuleName} object with the specified ID
	 * @throws IllegalArgumentException if no rule name with the given ID is found
	 */
	public RuleName getById(Integer id) {
		return ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
	}

	/**
	 * Delete {@link RuleName} by id.
	 *
	 * @param id the ID of the rule name to delete
	 */
	public void deleteById(Integer id) {
		ruleNameRepository.deleteById(id);
	}

	/**
	 * Gets the {@link RuleName} by name.
	 * 
	 * @param name the name to search for
	 * @return the {@link RuleName} object for the given name, or {@code null} if
	 *         not found
	 */
	public RuleName getByName(String name) {
		return ruleNameRepository.findByName(name);
	}
}
