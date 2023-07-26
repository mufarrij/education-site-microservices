package com.simplesolutions.studentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplesolutions.studentservice.model.User;

/**
 * Repository to be used to delegate CRUD operations on {@link User} entity against
 * the data source
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}