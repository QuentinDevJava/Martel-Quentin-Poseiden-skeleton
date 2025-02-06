package com.nnk.springboot.domain;

import java.sql.Timestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "bidlist")
public class BidList {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BidListId")
	private Integer bidListId;
	
	@Column(name="account")
	private String account;
	
	@Column(name="type")
	private String type;
	
	@Column(name="bidQuantiy")
	private Double bidQuantity;
	
	@Column(name="askQuantity")
	private Double askQuantity;
	
	@Column(name="bid")
	private Double bid;
	
	@Column(name="ask")
	private Double ask;	
	
	@Column(name="benchmark")
	private String benchmark;

	@Column(name="bidListDate")
	private Timestamp bidListDate;
	
	@Column(name="commentary")
	private String commentary;
	
	@Column(name="security")
	private String security;
	
	@Column(name="status")
	private String status;
	
	@Column(name="trader")
	private String trader;
	
	@Column(name="book")
	private String book;
	
	@Column(name="creationName")
	private String creationName;
	
	@Column(name="creationDate")
	private Timestamp creationDate;
	
	@Column(name="revisionName")
	private String revisionName;
	
	@Column(name="revisionDate")
	private Timestamp revisionDate;
	
	@Column(name="dealName")
	private String dealName;
	
	@Column(name="dealType")
	private String dealType;
	
	@Column(name="sourceListId")
	private String sourceListId;

	@Column(name="side")
	private String side;

	public BidList(String account, String type, Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}

}
