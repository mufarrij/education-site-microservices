package com.simplesolutions.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplesolutions.studentservice.model.Student;

/**
 * Repository to be used to delegate CRUD operations on {@link Student} entity against
 * the data source
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}