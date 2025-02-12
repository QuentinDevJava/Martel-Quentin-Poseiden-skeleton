package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class BidService {

	@Autowired
	private BidListRepository bidListRepository;

	public List<BidList> getAll() {
		return bidListRepository.findAll();
	}

	public void save(BidList bid) {
		bidListRepository.save(bid);
	}

	public BidList getById(Integer id) {
		return bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
	}

	public void deleteById(Integer id) {
		bidListRepository.deleteById(id);
	}

	public BidList getByAccount(String account) {
		return bidListRepository.findByAccount(account);
	}

}
