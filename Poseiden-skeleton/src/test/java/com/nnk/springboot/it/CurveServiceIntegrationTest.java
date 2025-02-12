package com.nnk.springboot.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;

@SpringBootTest
public class CurveServiceIntegrationTest {

	@Autowired
	private CurveService curveService;

	@Autowired
	private CurvePointRepository curvePointRepository;

	CurvePoint curve1;
	CurvePoint curve2;

	List<CurvePoint> curvePoints = new ArrayList<>();

	@BeforeEach
	void setUp() {
		curve1 = new CurvePoint(1, 20.00, 10.00);
		curve2 = new CurvePoint(1, 20.00, 20.00);
		curvePointRepository.deleteAll();
	}

	@Test
	void testGetAll() {
		// Arrange

		curvePointRepository.save(curve1);
		curvePointRepository.save(curve2);

		// Act
		List<CurvePoint> result = curveService.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		curveService.save(curve1);

		// Assert
		assertTrue(curvePointRepository.existsById(curve1.getId()));
	}

	@Test
	void testGetById() {
		// Arrange
		curvePointRepository.save(curve1);

		// Act
		CurvePoint result = curveService.getById(curve1.getId());

		// Assert
		assertNotNull(result);
		assertEquals(20.00, result.getTerm());
	}

	@Test
	void testGetByIdError() {
		int invalidId = 99999999;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			curveService.getById(invalidId);
		});

		assertEquals("Invalid CurvePoint Id:" + invalidId, exception.getMessage());
	}

	@Test
	void testDeleteById() {
		// Arrange
		curvePointRepository.save(curve1);

		// Act
		curveService.deleteById(1);

		// Assert
		assertFalse(curvePointRepository.existsById(1));
	}
}
