package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a bid list in the system.
 * 
 * This class maps to the "bidlist" table in the database.
 * 
 * <p>
 * <b>Constructor:</b>
 * </p>
 * <ul>
 * <li><b>{@link #BidList(String, String, Double)} :</b> Creates a new bid list
 * with the specified account, type, and bid quantity.</li>
 * </ul>
 * 
 * <p>
 * <b>Validation:</b>
 * </p>
 * <ul>
 * <li>{@link #account} and {@link #type} are required and cannot be blank.</li>
 * <li>{@link #bidQuantity} must be a non-null value greater than or equal to
 * 0.01.</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bidlist")
public class BidList {

	/** The bid list id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid_list_id")
	private Integer bidListId;

	/** The account. */
	@NotBlank(message = "Account is mandatory")
	@Column(name = "account")
	private String account;

	/** The type. */
	@NotBlank(message = "Type is mandatory")
	@Column(name = "type")
	private String type;

	/** The bid quantity. */
	@NotNull(message = "Bid quantity cannot be null.")
	@DecimalMin(value = "0.01", message = "Bid quantity must be greater than or equal to 0.01.")
	@Column(name = "bid_quantiy")
	private Double bidQuantity;

	/** The ask quantity. */
	@Column(name = "ask_quantity")
	private Double askQuantity;

	/** The bid. */
	@Column(name = "bid")
	private Double bid;

	/** The ask. */
	@Column(name = "ask")
	private Double ask;

	/** The benchmark. */
	@Column(name = "benchmark")
	private String benchmark;

	/** The bid list date. */
	@Column(name = "bid_list_date")
	private Timestamp bidListDate;

	/** The commentary. */
	@Column(name = "commentary")
	private String commentary;

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
	 * Instantiates a new bid list with the given account, type, and bid quantity.
	 * 
	 * @param account     the account associated with the bid
	 * @param type        the type of the bid
	 * @param bidQuantity the bid quantity
	 */
	public BidList(String account, String type, Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}

}
