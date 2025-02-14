package com.nnk.springboot.domain;

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
	@NotBlank(message = "MoodysRating is mandatory")
	private String moodysRating;

	/** The sand P rating. */
	@Column(name = "sandprating")
	@NotBlank(message = "SandPRating is mandatory")
	private String sandPRating;

	/** The fitch rating. */
	@Column(name = "fitch_rating")
	@NotBlank(message = "FitchRating is mandatory")
	private String fitchRating;

	/** The order number. */
	@Column(name = "order_number")
	@NotNull(message = "Order is mandatory")
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
