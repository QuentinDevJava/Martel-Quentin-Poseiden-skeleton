package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a rule name in the system.
 * 
 * This class maps to the "rulename" table in the database
 * 
 * <p>
 * <b>Constructor:</b>
 * </p>
 * <ul>
 * <li><b>{@link #RuleName(String, String, String, String, String, String)}
 * :</b> Creates a new RuleName with the specified name, description, json,
 * template, sql string, and sql part.</li>
 * </ul>
 * 
 * <p>
 * <b>Validation:</b>
 * </p>
 * <ul>
 * <li>{@link #name}, {@link #description}, {@link #json}, {@link #template},
 * {@link #sqlStr}, and {@link #sqlPart} must not be blank.</li>
 * </ul>
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "rulename")
public class RuleName {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/** The name. */
	@Column(name = "name")
	@NotBlank(message = "Name is mandatory")
	private String name;

	/** The description. */
	@Column(name = "description")
	@NotBlank(message = "Description is mandatory")
	private String description;

	/** The json. */
	@Column(name = "json")
	@NotBlank(message = "Json is mandatory")
	private String json;

	/** The template. */
	@Column(name = "template")
	@NotBlank(message = "Template is mandatory")
	private String template;

	/** The sql str. */
	@Column(name = "sql_str")
	@NotBlank(message = "SqlStr is mandatory")
	private String sqlStr;

	/** The sql part. */
	@Column(name = "sql_part")
	@NotBlank(message = "SqlPart is mandatory")
	private String sqlPart;

	/**
	 * Instantiates a new rule name.
	 *
	 * @param name        the name of the rule
	 * @param description the description of the rule
	 * @param json        the json of the rule
	 * @param template    the template of the rule
	 * @param sqlStr      the SQL string of the rule
	 * @param sqlPart     the SQL part of the rule
	 */
	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		super();
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

}
