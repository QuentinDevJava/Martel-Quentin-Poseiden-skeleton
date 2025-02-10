package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import jakarta.validation.Valid;

@Service
public class TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	public List<Trade> getAll() {
		return tradeRepository.findAll();
	}

	public void save(@Valid Trade trade) {
		tradeRepository.save(trade);
	}

	public Trade getById(Integer id) {
		return tradeRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		tradeRepository.deleteById(id);
	}

}
