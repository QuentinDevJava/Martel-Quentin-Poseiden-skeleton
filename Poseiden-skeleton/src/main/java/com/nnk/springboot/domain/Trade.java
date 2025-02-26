package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a trade in the system.
 *
 * This class maps to the "trade" table in the database.
 * 
 * <p>
 * <b>Constructor:</b>
 * </p>
 * <ul>
 * <li><b>{@link #Trade(String, String, Double)} :</b> Creates a new trade with
 * the specified account, type, and buy quantity.</li>
 * </ul>
 * 
 * <p>
 * <b>Validation:</b>
 * </p>
 * <ul>
 * <li>{@link #account} and {@link #type} must not be blank.</li>
 * <li>{@link #buyQuantity} must not be null.</li>
 * </ul>
 * 
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
	private Integer tradeId;

	/** The account. */
	@Column(name = "account")
	@NotBlank(message = "Account is mandatory")
	private String account;

	/** The type. */
	@Column(name = "type")
	@NotBlank(message = "Type is mandatory")
	private String type;

	/** The buy quantity. */
	@Column(name = "buy_quantity")
	@NotNull(message = "Buy Quantity is mandatory")
	private Double buyQuantity;

	/** The sell quantity. */
	@Column(name = "sell_quantity")
	private Double sellQuantity;

	/** The buy price. */
	@Column(name = "buy_price")
	private Double buyPrice;

	/** The sell price. */
	@Column(name = "sell_price")
	private Double sellPrice;

	/** The benchmark. */
	@Column(name = "benchmark")
	private String benchmark;

	/** The trade date. */
	@Column(name = "trade_date")
	private Timestamp tradeDate;

	/** The security. */
	@Column(name = "security")
	private String security;

	/** The status. */
	@Column(name = "status")
	private String status;

	/** The trader. */
	@Column(name = "trader")
	private String trader;

	/** The book. */
	@Column(name = "book")
	private String book;

	/** The creation name. */
	@Column(name = "creation_name")
	private String creationName;

	/** The creation date. */
	@Column(name = "creation_date")
	private Timestamp creationDate;

	/** The revision name. */
	@Column(name = "revision_name")
	private String revisionName;

	/** The revision date. */
	@Column(name = "revision_date")
	private Timestamp revisionDate;

	/** The deal name. */
	@Column(name = "deal_name")
	private String dealName;

	/** The deal type. */
	@Column(name = "deal_type")
	private String dealType;

	/** The source list id. */
	@Column(name = "source_list_id")
	private String sourceListId;

	/** The side. */
	@Column(name = "side")
	private String side;

	/**
	 * Instantiates a new trade with the specified account, type, and buy quantity.
	 *
	 * @param account     the account associated with the trade
	 * @param type        the type of trade
	 * @param buyQuantity the quantity of the trade
	 */
	public Trade(String account, String type, Double buyQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.buyQuantity = buyQuantity;
	}
}
