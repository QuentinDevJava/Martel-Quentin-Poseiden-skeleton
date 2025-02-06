package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "CurveId")
	private Integer curveId;

	@Column(name = "asOfDate")
	private Timestamp asOfDate;

	@Column(name = "term")
	private Double term;

	@Column(name = "value")
	private Double value;

	@Column(name = "creationDate")
	private Timestamp creationDate;

}
