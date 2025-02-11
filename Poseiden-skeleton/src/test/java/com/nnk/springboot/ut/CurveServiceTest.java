package com.nnk.springboot.ut;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;

@SpringBootTest
class CurveServiceTest {

	@Mock
	private CurvePointRepository curvePointRepository;

	@InjectMocks
	private CurveService curveService;

	CurvePoint curve1;
	CurvePoint curve2;

	List<CurvePoint> curvePoints = new ArrayList<>();

	@BeforeEach
	void setUp() {
		curve1 = new CurvePoint(1, 20.00, 10.00);
		curve2 = new CurvePoint(1, 20.00, 20.00);
	}

	@Test
	void testGetAll() {
		// Arrange
		curvePoints.add(curve1);
		curvePoints.add(curve2);

		when(curvePointRepository.findAll()).thenReturn(curvePoints);

		// Act
		curveService.getAll();

		// Assert
		verify(curvePointRepository, times(1)).findAll();
	}

	@Test
	void testSave() {
		// Arrange

		// Act
		curveService.save(curve1);

		// Assert
		verify(curvePointRepository, times(1)).save(curve1);
	}

	@Test
	void testGetById() {
		// Arrange
		when(curvePointRepository.findById(1)).thenReturn(Optional.of(curve1));

		// Act
		curveService.getById(1);

		// Assert

		verify(curvePointRepository, times(1)).findById(1);
	}

	@Test
	void testGetByIdNotFound() {
		// Arrange
		when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

		// Act
		CurvePoint result = curveService.getById(1);

		// Assert
		assertNull(result);
		verify(curvePointRepository, times(1)).findById(1);
	}

	@Test
	void testDeleteById() {
		// Act
		curveService.deleteById(1);

		// Assert
		verify(curvePointRepository, times(1)).deleteById(1);
	}
}
