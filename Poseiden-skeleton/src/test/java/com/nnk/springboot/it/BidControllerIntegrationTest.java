package com.nnk.springboot.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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

		bid1 = bidService.getByAccount("updateTest");
		assertNull(bid1);

		mockMvc.perform(post("/bidList/validate")

				.param("account", "updateTest")

				.param("type", "updateTest")

				.param("bidQuantity", "10.00")

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isOk())

				.andExpect(view().name("bidList/add"));

		bid1 = bidService.getByAccount("updateTest");

		assertNotNull(bid1);

		assertEquals("updateTest", bid1.getType());
		assertEquals(10.00, bid1.getBidQuantity());

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
		bid1 = bidService.getByAccount("Account test");
		int bidId = bid1.getBidListId();
		mockMvc.perform(get("/bidList/delete/{id}", bidId)).andExpect(status().isFound()).andDo(print())
				.andExpect(redirectedUrl("/bidList/list"));
		bid1 = bidService.getById(bidId);
		assertNull(bid1);
	}
}