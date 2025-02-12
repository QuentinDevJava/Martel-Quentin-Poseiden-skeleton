package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bidlist")
public class BidList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid_list_id")
	private Integer bidListId;

	@NotBlank
	@Column(name = "account")
	private String account;

	@NotBlank
	@Column(name = "type")
	private String type;

	@NotNull(message = "Bid quantity cannot be null.")
	@Min(value = 1, message = "Bid quantity must be greater than or equal to 1.")
	@Column(name = "bid_quantiy")
	private Double bidQuantity;

	@Column(name = "ask_quantity")
	private Double askQuantity;

	@Column(name = "bid")
	private Double bid;

	@Column(name = "ask")
	private Double ask;

	@Column(name = "benchmark")
	private String benchmark;

	@Column(name = "bid_list_date")
	private Timestamp bidListDate;

	@Column(name = "commentary")
	private String commentary;

	@Column(name = "security")
	private String security;

	@Column(name = "status")
	private String status;

	@Column(name = "trader")
	private String trader;

	@Column(name = "book")
	private String book;

	@Column(name = "creation_name")
	private String creationName;

	@Column(name = "creation_date")
	private Timestamp creationDate;

	@Column(name = "revision_name")
	private String revisionName;

	@Column(name = "revision_date")
	private Timestamp revisionDate;

	@Column(name = "deal_name")
	private String dealName;

	@Column(name = "deal_type")
	private String dealType;

	@Column(name = "source_list_id")
	private String sourceListId;

	@Column(name = "side")
	private String side;

	public BidList(String account, String type, Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}

}
