package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;

/**
 * The Interface UserRepository.
 * 
 * <p>
 * Repository interface for accessing {@link User} entities from the database.
 * This interface extends {@link JpaRepository} for basic CRUD operations and
 * {@link JpaSpecificationExecutor} for advanced query capabilities.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the {@link User} or null if no user is found
	 */
	User findByUsername(String username);

}