package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.CurvePoint;

/**
 * The Interface CurvePointRepository.
 * 
 * <p>
 * Repository interface for accessing {@link CurvePoint} entities from the
 * database. This interface extends {@link JpaRepository} for basic CRUD
 * operations.
 * </p>
 */
@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

	/**
	 * Find by curveId.
	 *
	 * @param curveId the curve id
	 * @return the {@link CurvePoint} or null no curve point is found
	 */
	CurvePoint findByCurveId(Integer curveId);

}
