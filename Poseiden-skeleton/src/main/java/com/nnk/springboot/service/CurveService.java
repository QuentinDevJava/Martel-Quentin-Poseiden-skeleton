package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

/**
 * The Class CurveService.
 */
@NoArgsConstructor
@Service
public class CurveService {

	/** The curve point repository. */
	@Autowired
	private CurvePointRepository curvePointRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<CurvePoint> getAll() {
		return curvePointRepository.findAll();
	}

	/**
	 * Save.
	 *
	 * @param curvePoint the curve point
	 */
	public void save(@Valid CurvePoint curvePoint) {
		curvePointRepository.save(curvePoint);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public CurvePoint getById(Integer id) {
		return curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Integer id) {
		curvePointRepository.deleteById(id);
	}

	/**
	 * Gets the by curve id.
	 *
	 * @param curveId the curve id
	 * @return the by curve id
	 */
	public CurvePoint getByCurveId(Integer curveId) {
		return curvePointRepository.findByCurveId(curveId);
	}

}
