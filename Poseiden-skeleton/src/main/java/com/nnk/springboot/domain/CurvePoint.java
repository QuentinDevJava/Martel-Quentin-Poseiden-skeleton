package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a curve point in the system.
 * 
 * This class maps to the "curvepoint" table in the database.
 * 
 * <p>
 * <b>Constructor:</b>
 * </p>
 * <ul>
 * <li><b>{@link #CurvePoint(Integer, Double, Double)} :</b> Creates a new curve
 * point with the specified curve ID, term, and value.</li>
 * </ul>
 * 
 * <p>
 * <b>Validation:</b>
 * </p>
 * <ul>
 * <li>{@link #term} and {@link #value} must not be null and must be greater
 * than or equal to 0.01.</li>
 * </ul>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/** The curve id. */
	@Column(name = "curve_id")
	private Integer curveId;

	/** The as of date. */
	@Column(name = "as_of_date")
	private Timestamp asOfDate;

	/** The term. */
	@Column(name = "term")
	@NotNull(message = "Term is mandatory")
	@DecimalMin(value = "0.01", message = "Term must be greater than or equal to 0.01.")
	private Double term;

	/** The value. */
	@Column(name = "value_col")
	@NotNull(message = "Value is mandatory")
	@DecimalMin(value = "0.01", message = "Term must be greater than or equal to 0.01.")
	private Double value;

	/** The creation date. */
	@Column(name = "creation_date")
	private Timestamp creationDate;

	/**
	 * Instantiates a new curve point with the specified curve ID, term, and value.
	 * 
	 * @param curveId the curve ID this point belongs to
	 * @param term    the term associated with the curve point
	 * @param value   the value associated with the curve point
	 */
	public CurvePoint(Integer curveId, Double term, Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

}
