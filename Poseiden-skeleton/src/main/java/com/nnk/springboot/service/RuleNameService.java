package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class RuleNameService {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	public List<RuleName> getAll() {
		return ruleNameRepository.findAll();
	}

	public void save(RuleName ruleName) {
		ruleNameRepository.save(ruleName);
	}

	public RuleName getById(Integer id) {
		return ruleNameRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		ruleNameRepository.deleteById(id);
	}

}
