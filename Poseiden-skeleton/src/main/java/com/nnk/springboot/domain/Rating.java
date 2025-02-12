package com.nnk.springboot.domain;

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
 * The Class Rating.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "rating")
public class Rating {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	/** The moodys rating. */
	@Column(name = "moodys_rating")
	private String moodysRating;

	/** The sand P rating. */
	@Column(name = "sandprating")
	private String sandPRating;

	/** The fitch rating. */
	@Column(name = "fitch_rating")
	private String fitchRating;

	/** The order number. */
	@Column(name = "order_number")
	private Integer orderNumber;

	/**
	 * Instantiates a new rating.
	 *
	 * @param moodysRating the moodys rating
	 * @param sandPRating  the sand P rating
	 * @param fitchRating  the fitch rating
	 * @param orderNumber  the order number
	 */
	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		super();
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}
}
