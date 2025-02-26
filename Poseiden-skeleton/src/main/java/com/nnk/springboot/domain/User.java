package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a user in the system.
 * 
 * This class maps to the "users" table in the database.
 * <p>
 * <b>Constructor:</b>
 * </p>
 * <ul>
 * <li><b>{@link #User(String, String, String, String)} :</b> Creates a new user
 * with the specified fullname, username, password, and role.</li>
 * </ul>
 * 
 * <p>
 * <b>Validation:</b>
 * </p>
 * <ul>
 * <li>{@link #username}, {@link #password}, {@link #fullname}, and
 * {@link #role} must not be blank.</li>
 * <li>{@link #password} must match the regular expression: at least 8
 * characters long, containing at least one uppercase letter, one lowercase
 * letter, one number, and one special character.</li>
 * </ul>
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/** The username. */
	@Column(name = "username")
	@NotBlank(message = "Username is mandatory")
	private String username;

	/**
	 * 
	 * The password must be at least 8 characters long, containing at least one
	 * uppercase letter, one lowercase letter, one number, and one special
	 * character.
	 * 
	 */
	@Column(name = "password")
	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,1000}$", message = "The password must contain at least 8 characters,, including at least one uppercase letter, one lowercase letter, one number, and one special character.")
	private String password;

	/** The fullname. */
	@Column(name = "fullname")
	@NotBlank(message = "FullName is mandatory")
	private String fullname;

	/** The role. */
	@Column(name = "role")
	@NotBlank(message = "Role is mandatory")
	private String role;

	/**
	 * Instantiates a new user with the specified full name, username, password, and
	 * role.
	 *
	 * @param fullname the full name of the user
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param role     the role of the user
	 */
	public User(String fullname, String username, String password, String role) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.role = role;
	}

}
