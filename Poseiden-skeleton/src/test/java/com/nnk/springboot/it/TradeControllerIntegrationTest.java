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
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = { "ADMIN" })
class TradeControllerIntegrationTest {

	@Autowired
	private TradeService tradeService;

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private MockMvc mockMvc;

	Trade trade;
	Trade trade2;

	List<BidList> bids = new ArrayList<>();

	@BeforeEach
	void setUp() {
		trade = new Trade("test1", "test1", 10.00);
		trade2 = new Trade("test2", "test2", 20.00);
		tradeRepository.deleteAll();
	}

	@Test
	void testGetTradeList() throws Exception {
		tradeService.save(trade);
		tradeService.save(trade2);

		mockMvc.perform(get("/trade/list"))

				.andExpect(status().isOk())

				.andDo(print())

				.andExpect(view().name("trade/list"))

				.andExpect(model().attributeExists("trades"))

				.andExpect(model().attributeExists("username"));
	}

	@Test
	void testAddTradeForm() throws Exception {
		mockMvc.perform(get("/trade/add"))

				.andExpect(status().isOk())

				.andExpect(view().name("trade/add"));
	}

	@Test
	void testValidateTrade() throws Exception {

		Trade tradeTest = tradeService.getByAccount(trade.getAccount());
		assertNull(tradeTest);

		mockMvc.perform(post("/trade/validate")

				.param("account", trade.getAccount())

				.param("type", trade.getType())

				.param("buyQuantity", trade.getBuyQuantity().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(view().name("redirect:/trade/list"));

		tradeTest = tradeService.getByAccount(trade.getAccount());

		assertNotNull(tradeTest);

		assertEquals(trade.getType(), tradeTest.getType());

	}

	@Test
	void testValidateTradeErrorForm() throws Exception {

		Trade tradeTest = tradeService.getByAccount(trade.getAccount());
		assertNull(tradeTest);

		mockMvc.perform(post("/trade/validate")

				.param("account", trade.getAccount())

				.param("type", "")

				.param("buyQuantity", trade.getBuyQuantity().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isOk())

				.andExpect(view().name("trade/add"));

		tradeTest = tradeService.getByAccount(trade.getAccount());

		assertNull(tradeTest);
	}

	@Test
	void testShowUpdateForm() throws Exception {
		tradeService.save(trade);
		trade = tradeService.getByAccount(trade.getAccount());
		int tradeId = trade.getTradeId();

		mockMvc.perform(get("/trade/update/{id}", tradeId))

				.andExpect(status().isOk())

				.andExpect(model().attributeExists("trade"))

				.andExpect(view().name("trade/update"));

	}

	@Test
	void testUpdateTrade() throws Exception {

		tradeService.save(trade);
		trade = tradeService.getByAccount(trade.getAccount());
		int tradeId = trade.getTradeId();

		trade.setType("Test");

		mockMvc.perform(post("/trade/update/{id}", tradeId)

				.param("account", trade.getAccount())

				.param("type", trade.getType())

				.param("buyQuantity", trade.getBuyQuantity().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isFound())

				.andExpect(redirectedUrl("/trade/list"));

		trade = tradeService.getById(tradeId);

		assertEquals("Test", trade.getType());

	}

	@Test
	void testUpdateTradeErrorForm() throws Exception {

		tradeService.save(trade);
		trade = tradeService.getByAccount(trade.getAccount());
		int tradeId = trade.getTradeId();

		trade.setType("Test");

		mockMvc.perform(post("/trade/update/{id}", tradeId)

				.param("account", trade.getAccount())

				.param("type", "")

				.param("buyQuantity", trade.getBuyQuantity().toString())

				.with(csrf()))

				.andDo(print())

				.andExpect(status().isOk())

				.andExpect(view().name("trade/update"));

		trade = tradeService.getById(tradeId);

		assertEquals("test1", trade.getType());

	}

	@Test
	void testDeleteTrade() throws Exception {
		tradeService.save(trade);
		trade = tradeService.getByAccount(trade.getAccount());
		int tradeId = trade.getTradeId();
		mockMvc.perform(get("/trade/delete/{id}", tradeId))

				.andExpect(status().isFound())

				.andDo(print())

				.andExpect(redirectedUrl("/trade/list"));

		List<Trade> tradeTest = tradeService.getAll();
		assertTrue(tradeTest.isEmpty());
	}
}