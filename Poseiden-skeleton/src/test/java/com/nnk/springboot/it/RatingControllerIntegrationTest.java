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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "testuser", roles = { "ADMIN" })
class RatingControllerIntegrationTest {

	@Autowired
	private RatingService ratingService;

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private MockMvc mockMvc;

	Rating rating;
	Rating rating2;

	List<BidList> bids = new ArrayList<>();

	@BeforeEach
	void setUp() {
		rating = new Rating("Moodys Rating1", "Sand PRating1", "Fitch Rating1", 10);
		rating2 = new Rating("Moodys Rating2", "Sand PRating2", "Fitch Rating2", 11);
		ratingRepository.deleteAll();
	}

	@Test
	void testGetCurvePointList() throws Exception {
		ratingService.save(rating);
		ratingService.save(rating2);

		mockMvc.perform(get("/rating/list"))

				.andExpect(status().isOk())

				.andDo(print())

				.andExpect(view().name("rating/list"))

				.andExpect(model().attributeExists("ratings"))

				.andExpect(model().attributeExists("username"));
	}

	@Test
	void testAddCurvePointForm() throws Exception {
		mockMvc.perform(get("/rating/add"))

				.andExpect(status().isOk())

				.andExpect(view().name("rating/add"));
	}

	@Test
	void testValidate() throws Exception {

		Rating ratingTest = ratingService.getByMoodysRating(rating.getMoodysRating());

		assertNull(ratingTest);

		mockMvc.perform(post("/rating/validate")

				.param("moodysRating", rating.getMoodysRating())

				.param("sandPRating", rating.getSandPRating())

				.param("fitchRating", rating.getFitchRating())

				.param("orderNumber", rating.getOrderNumber().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isOk())

				.andExpect(view().name("rating/add"));

		ratingTest = ratingService.getByMoodysRating(rating.getMoodysRating());

		assertNotNull(rating);

		assertEquals(rating.getFitchRating(), ratingTest.getFitchRating());
		assertEquals(rating.getSandPRating(), ratingTest.getSandPRating());
		assertEquals(rating.getOrderNumber(), ratingTest.getOrderNumber());

	}

	@Test
	void testShowUpdateForm() throws Exception {
		ratingService.save(rating);
		rating = ratingService.getByMoodysRating(rating.getMoodysRating());
		int ratingId = rating.getId();

		mockMvc.perform(get("/rating/update/{id}", ratingId))

				.andExpect(status().isOk())

				.andExpect(model().attributeExists("rating"))

				.andExpect(view().name("rating/update"));

	}

	@Test
	void testUpdateRating() throws Exception {

		ratingService.save(rating);
		rating = ratingService.getByMoodysRating(rating.getMoodysRating());
		int ratingId = rating.getId();

		rating.setOrderNumber(200);

		mockMvc.perform(post("/rating/update/{id}", ratingId)

				.param("moodysRating", rating.getMoodysRating())

				.param("fitchRating", rating.getFitchRating())

				.param("sandPRating", rating.getSandPRating())

				.param("orderNumber", rating.getOrderNumber().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(redirectedUrl("/rating/list"));

		rating = ratingService.getById(ratingId);

		assertEquals(200, rating.getOrderNumber());

	}

	@Test
	void testDeleteCurvePoint() throws Exception {
		ratingService.save(rating);
		rating = ratingService.getByMoodysRating(rating.getMoodysRating());
		int ratingId = rating.getId();
		mockMvc.perform(get("/rating/delete/{id}", ratingId))

				.andExpect(status().isFound())

				.andDo(print())

				.andExpect(redirectedUrl("/rating/list"));

		List<Rating> ratingTest = ratingService.getAll();
		assertTrue(ratingTest.isEmpty());
	}
}