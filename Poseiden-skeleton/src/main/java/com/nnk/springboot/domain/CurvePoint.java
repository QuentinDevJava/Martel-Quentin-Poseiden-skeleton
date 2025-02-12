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
 * The Class CurvePoint.
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
	private Double term;

	/** The value. */
	@Column(name = "value")
	private Double value;

	/** The creation date. */
	@Column(name = "creation_date")
	private Timestamp creationDate;

	/**
	 * Instantiates a new curve point.
	 *
	 * @param curveId the curve id
	 * @param term    the term
	 * @param value   the value
	 */
	public CurvePoint(Integer curveId, Double term, Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

}
