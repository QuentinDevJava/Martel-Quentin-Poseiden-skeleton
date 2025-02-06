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

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	@NotBlank(message = "Username is mandatory")
	private String username;

	@Column(name = "password")
	@NotBlank(message = "Password is mandatory")
	private String password;

	@Column(name = "fullname")
	@NotBlank(message = "FullName is mandatory")
	private String fullname;

	@Column(name = "role")
	@NotBlank(message = "Role is mandatory")
	private String role;

}
