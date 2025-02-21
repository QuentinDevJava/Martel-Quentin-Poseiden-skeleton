package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.CurvePoint;

/**
 * The Interface CurvePointRepository.
 */
@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

	/**
	 * Find by curve id.
	 *
	 * @param curveId the curve id
	 * @return the curve point
	 */
	CurvePoint findByCurveId(Integer curveId);

}
