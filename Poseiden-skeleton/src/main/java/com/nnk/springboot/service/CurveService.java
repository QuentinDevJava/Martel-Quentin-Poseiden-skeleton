package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class CurveService {

	@Autowired
	private CurvePointRepository curvePointRepository;

	public List<CurvePoint> getAll() {
		return curvePointRepository.findAll();
	}

	public void save(@Valid CurvePoint curvePoint) {
		curvePointRepository.save(curvePoint);
	}

	public CurvePoint getById(Integer id) {
		return curvePointRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		curvePointRepository.deleteById(id);
	}

}
