package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class Trade.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "trade")
public class Trade {

	/** The trade id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trade_id")
	Integer tradeId;

	/** The account. */
	@Column(name = "account")
	String account;

	/** The type. */
	@Column(name = "type")
	String type;

	/** The buy quantity. */
	@Column(name = "buy_quantity")
	Double buyQuantity;

	/** The sell quantity. */
	@Column(name = "sell_quantity")
	Double sellQuantity;

	/** The buy price. */
	@Column(name = "buy_price")
	Double buyPrice;

	/** The sell price. */
	@Column(name = "sell_price")
	Double sellPrice;

	/** The benchmark. */
	@Column(name = "benchmark")
	String benchmark;

	/** The trade date. */
	@Column(name = "trade_date")
	Timestamp tradeDate;

	/** The security. */
	@Column(name = "security")
	String security;

	/** The status. */
	@Column(name = "status")
	String status;

	/** The trader. */
	@Column(name = "trader")
	String trader;

	/** The book. */
	@Column(name = "book")
	String book;

	/** The creation name. */
	@Column(name = "creation_name")
	String creationName;

	/** The creation date. */
	@Column(name = "creation_date")
	Timestamp creationDate;

	/** The revision name. */
	@Column(name = "revision_name")
	String revisionName;

	/** The revision date. */
	@Column(name = "revision_date")
	Timestamp revisionDate;

	/** The deal name. */
	@Column(name = "deal_name")
	String dealName;

	/** The deal type. */
	@Column(name = "deal_type")
	String dealType;

	/** The source list id. */
	@Column(name = "source_list_id")
	String sourceListId;

	/** The side. */
	@Column(name = "side")
	String side;

	/**
	 * Instantiates a new trade.
	 *
	 * @param account the account
	 * @param type    the type
	 */
	public Trade(String account, String type) {
		super();
		this.account = account;
		this.type = type;
	}
}
