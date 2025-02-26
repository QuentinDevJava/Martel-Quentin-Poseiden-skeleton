package com.nnk.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * The Class CurveService.
 *
 * <p>
 * Service class responsible for managing {@link CurvePoint} entities.
 * </p>
 * 
 * <p>
 * <b>Key Methods:</b>
 * </p>
 * <ul>
 * <li>{@link #getAll()} - Retrieves all curve points from the repository.</li>
 * <li>{@link #save(CurvePoint)} - Saves a new or existing curve point to the
 * repository.</li>
 * <li>{@link #getById(Integer)} - Retrieves a curve point by its ID.</li>
 * <li>{@link #deleteById(Integer)} - Deletes a curve point by its ID.</li>
 * <li>{@link #getByCurveId(Integer)} - Retrieves a curve point by its curve
 * ID.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Service
public class CurveService {

	/** The curve point repository. */
	private final CurvePointRepository curvePointRepository;

	/**
	 * Gets all {@link CurvePoint}.
	 * 
	 * @return the list of all {@link CurvePoint} entities
	 */
	public List<CurvePoint> getAll() {
		return curvePointRepository.findAll();
	}

	/**
	 * Save a {@link CurvePoint}.
	 *
	 * @param curvePoint the {@link CurvePoint} object to be saved
	 */
	public void save(@Valid CurvePoint curvePoint) {
		curvePointRepository.save(curvePoint);
	}

	/**
	 * Gets the {@link CurvePoint} by id.
	 *
	 * @param id the ID of the curve point to retrieve
	 * @return the {@link CurvePoint} object with the specified ID
	 * @throws IllegalArgumentException if no curve point with the given ID is found
	 */
	public CurvePoint getById(Integer id) {
		return curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
	}

	/**
	 * Delete {@link CurvePoint} by id.
	 *
	 * @param id the ID of the curve point to delete
	 */
	public void deleteById(Integer id) {
		curvePointRepository.deleteById(id);
	}

	/**
	 * Gets the {@link CurvePoint} by curve ID.
	 *
	 * @param curveId the curve ID to search for
	 * @return the {@link CurvePoint} object for the given curve ID, or {@code null}
	 *         if not found
	 */
	public CurvePoint getByCurveId(Integer curveId) {
		return curvePointRepository.findByCurveId(curveId);
	}
}