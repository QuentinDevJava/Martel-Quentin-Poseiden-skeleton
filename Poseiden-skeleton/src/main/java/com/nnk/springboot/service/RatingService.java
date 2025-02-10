package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}

	public void save(Rating rating) {
		ratingRepository.save(rating);
	}

	public Rating getById(Integer id) {
		return ratingRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		ratingRepository.deleteById(id);
	}

}
