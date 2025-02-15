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
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = { "ADMIN" })
class BidControllerIntegrationTest {

	@Autowired
	private BidService bidService;

	@Autowired
	private BidListRepository bidListRepository;

	@Autowired
	private MockMvc mockMvc;

	BidList bid1;
	BidList bid2;

	List<BidList> bids = new ArrayList<>();

	@BeforeEach
	void setUp() {
		bid1 = new BidList("Account test", "Type test", 10.00);
		bid2 = new BidList("Account test2", "Type test2", 20.00);
		bidListRepository.deleteAll();
	}

	@Test
	void testGetBidList() throws Exception {
		bidService.save(bid1);
		bidService.save(bid2);

		mockMvc.perform(get("/bidList/list"))

				.andExpect(status().isOk())

				.andDo(print())

				.andExpect(view().name("bidList/list"))

				.andExpect(model().attributeExists("bidLists"))

				.andExpect(model().attributeExists("username"));
	}

	@Test
	void testAddBidForm() throws Exception {
		mockMvc.perform(get("/bidList/add"))

				.andExpect(status().isOk())

				.andExpect(view().name("bidList/add"));
	}

	@Test
	void testValidateBid() throws Exception {

		BidList bidTest = bidService.getByAccount(bid1.getAccount());
		assertNull(bidTest);

		mockMvc.perform(post("/bidList/validate")

				.param("account", bid1.getAccount())

				.param("type", bid1.getType())

				.param("bidQuantity", bid1.getBidQuantity().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(view().name("redirect:/bidList/list"));

		bidTest = bidService.getByAccount(bid1.getAccount());

		assertNotNull(bid1);

		assertEquals(bid1.getType(), bidTest.getType());
		assertEquals(bid1.getBidQuantity(), bidTest.getBidQuantity());

	}

	@Test
	void testShowUpdateForm() throws Exception {
		bidService.save(bid1);
		bid1 = bidService.getByAccount("Account test");
		int bidId = bid1.getBidListId();

		mockMvc.perform(get("/bidList/update/{id}", bidId))

				.andExpect(status().isOk())

				.andExpect(model().attributeExists("bidList"))

				.andExpect(view().name("bidList/update"));

	}

	@Test
	void testUpdateBid() throws Exception {

		bidService.save(bid1);
		bid1 = bidService.getByAccount("Account test");
		int bidId = bid1.getBidListId();

		bid1.setBidQuantity(200.00);

		mockMvc.perform(post("/bidList/update/{id}", bidId)

				.param("bidListId", String.valueOf(bidId))

				.param("type", bid1.getType())

				.param("account", bid1.getAccount())

				.param("bidQuantity", "200.00")

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(redirectedUrl("/bidList/list"));

		bid1 = bidService.getById(bidId);

		assertEquals(200, bid1.getBidQuantity());

	}

	@Test
	void testDeleteBid() throws Exception {
		bidService.save(bid1);
		bid1 = bidService.getByAccount(bid1.getAccount());
		int bidId = bid1.getBidListId();
		mockMvc.perform(get("/bidList/delete/{id}", bidId))

				.andExpect(status().isFound())

				.andDo(print())

				.andExpect(redirectedUrl("/bidList/list"));

		List<BidList> bidTest = bidService.getAll();
		assertTrue(bidTest.isEmpty());
	}
}