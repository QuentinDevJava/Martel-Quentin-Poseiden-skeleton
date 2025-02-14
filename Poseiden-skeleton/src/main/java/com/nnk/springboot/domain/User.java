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

// TODO: Auto-generated Javadoc
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
