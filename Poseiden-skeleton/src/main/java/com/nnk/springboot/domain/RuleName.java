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
 * The Class RuleName.
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
	private String name;

	/** The description. */
	@Column(name = "description")
	private String description;

	/** The json. */
	@Column(name = "json")
	private String json;

	/** The template. */
	@Column(name = "template")
	private String template;

	/** The sql str. */
	@Column(name = "sql_str")
	private String sqlStr;

	/** The sql part. */
	@Column(name = "sql_part")
	private String sqlPart;

	/**
	 * Instantiates a new rule name.
	 *
	 * @param name        the name
	 * @param description the description
	 * @param json        the json
	 * @param template    the template
	 * @param sqlStr      the sql str
	 * @param sqlPart     the sql part
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
