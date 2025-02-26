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
 * Represents a rating in the system.
 * 
 * This class maps to the "rating" table in the database.
 * 
 * <p>
 * <b>Constructor:</b>
 * </p>
 * <ul>
 * <li><b>{@link #Rating(String, String, String, Integer)} :</b> Creates a new
 * rating with the specified Moody's rating, Sand rating, Fitch rating, and
 * order number.</li>
 * </ul>
 * 
 * <p>
 * <b>Validation:</b>
 * </p>
 * <ul>
 * <li>{@link #moodysRating}, {@link #sandPRating}, and {@link #fitchRating}
 * must not be blank.</li>
 * <li>{@link #orderNumber} must not be null.</li>
 * </ul>
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
	 * Instantiates a new rating with the specified Moody's rating, Sand rating,
	 * Fitch rating, and order number.
	 *
	 * @param moodysRating the Moody's rating
	 * @param sandPRating  the Sand rating
	 * @param fitchRating  the Fitch rating
	 * @param orderNumber  the order number of the rating
	 */
	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		super();
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}
}
