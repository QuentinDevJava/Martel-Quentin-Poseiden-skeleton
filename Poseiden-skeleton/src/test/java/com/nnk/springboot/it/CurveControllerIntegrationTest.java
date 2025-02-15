package com.nnk.springboot.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "testuser", roles = { "ADMIN" })
class CurveControllerIntegrationTest {

	@Autowired
	private CurveService curveService;

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Autowired
	private MockMvc mockMvc;

	CurvePoint curvePoint;
	CurvePoint curvePoint2;

	List<BidList> bids = new ArrayList<>();

	@BeforeEach
	void setUp() {
		curvePoint = new CurvePoint(1, 2.00, 10.00);
		curvePoint2 = new CurvePoint(2, 4.00, 20.00);
		curvePointRepository.deleteAll();
	}

	@Test
	void testGetCurvePointList() throws Exception {
		curveService.save(curvePoint);
		curveService.save(curvePoint2);

		mockMvc.perform(get("/curvePoint/list"))

				.andExpect(status().isOk())

				.andDo(print())

				.andExpect(view().name("curvePoint/list"))

				.andExpect(model().attributeExists("curvePoints"))

				.andExpect(model().attributeExists("username"));
	}

	@Test
	void testAddCurvePointForm() throws Exception {
		mockMvc.perform(get("/curvePoint/add"))

				.andExpect(status().isOk())

				.andExpect(view().name("curvePoint/add"));
	}

	@Test
	void testValidateCurvePoint() throws Exception {

		CurvePoint curvePointTest = curveService.getByCurveId(curvePoint.getCurveId());
		assertNull(curvePointTest);

		mockMvc.perform(post("/curvePoint/validate")

				.param("curveId", curvePoint.getCurveId().toString())

				.param("term", curvePoint.getTerm().toString())

				.param("value", curvePoint.getValue().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(view().name("redirect:/curvePoint/list"));

		curvePointTest = curveService.getByCurveId(curvePoint.getCurveId());

		assertNotNull(curvePoint);

		assertEquals(curvePoint.getTerm(), curvePointTest.getTerm());
		assertEquals(curvePoint.getValue(), curvePointTest.getValue());

	}

	@Test
	void testShowUpdateForm() throws Exception {
		curveService.save(curvePoint);
		curvePoint = curveService.getByCurveId(curvePoint.getCurveId());
		int curvePointId = curvePoint.getId();

		mockMvc.perform(get("/curvePoint/update/{id}", curvePointId))

				.andExpect(status().isOk())

				.andExpect(model().attributeExists("curvePoint"))

				.andExpect(view().name("curvePoint/update"));

	}

	@Test
	void testUpdateCurvePoint() throws Exception {

		curveService.save(curvePoint);
		curvePoint = curveService.getByCurveId(curvePoint.getCurveId());
		int curvePointId = curvePoint.getId();

		curvePoint.setValue(200.00);

		mockMvc.perform(post("/curvePoint/update/{id}", curvePointId)

				.param("curveId", curvePoint.getCurveId().toString())

				.param("term", curvePoint.getTerm().toString())

				.param("value", curvePoint.getValue().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(redirectedUrl("/curvePoint/list"));

		curvePoint = curveService.getById(curvePointId);

		assertEquals(200, curvePoint.getValue());

	}

	@Test
	void testDeleteCurvePoint() throws Exception {
		curveService.save(curvePoint);
		curvePoint = curveService.getByCurveId(curvePoint.getCurveId());
		int curvePointId = curvePoint.getId();
		mockMvc.perform(get("/curvePoint/delete/{id}", curvePointId))

				.andExpect(status().isFound())

				.andDo(print())

				.andExpect(redirectedUrl("/curvePoint/list"));

		List<CurvePoint> curvePointTest = curveService.getAll();
		assertTrue(curvePointTest.isEmpty());
	}
}