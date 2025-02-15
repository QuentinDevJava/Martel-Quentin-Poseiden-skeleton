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
import lombok.NoArgsConstructor;

/**
 * The Class User.
 */
@Data
@NoArgsConstructor
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

	/** The password. */
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

}
