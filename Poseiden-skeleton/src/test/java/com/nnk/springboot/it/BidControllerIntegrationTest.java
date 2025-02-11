package com.nnk.springboot.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = "ADMIN")
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
		mockMvc.perform(get("/bidList/add")).andExpect(status().isOk()).andExpect(view().name("bidList/add"));
	}

	// TODO voir pour utilisation webmockmvc dans le context spring security
	// @AutoConfigureMockMvc @WithMockUser
	@Test
	void testValidateBid() throws Exception {

		mockMvc.perform(post("/bidList/validate").param("account", bid1.getAccount()).param("type", bid1.getType())
				.param("bidQuantity", bid1.getBidQuantity().toString())).andExpect(status().isOk())
				.andExpect(view().name("bidList/add"));
	}

	@Test
	void testShowUpdateForm() throws Exception {
		bidService.save(bid1);

		mockMvc.perform(get("/bidList/update/{id}", 1)).andExpect(status().isOk())
				.andExpect(view().name("bidList/update")).andExpect(model().attributeExists("bidList"));
	}

	@Test
	void testUpdateBid() throws Exception {
		bidService.save(bid1);

		bid1.setBidListId(1);
		bid1.setBidQuantity(200.00);
		mockMvc.perform(post("/bidList/update/{id}", 1).flashAttr("bidList", bid1))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/bidList/list"));
	}

	@Test
	void testDeleteBid() throws Exception {
		bidService.save(bid1);
		mockMvc.perform(get("/bidList/delete/{id}", 1)).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(view().name("redirect:/bidList/list"));
	}
}