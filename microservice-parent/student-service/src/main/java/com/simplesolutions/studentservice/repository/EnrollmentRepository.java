package com.simplesolutions.studentservice.repository;

import com.simplesolutions.studentservice.model.Enrollment;
import com.simplesolutions.studentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to be used to delegate CRUD operations on {@link Student} entity against
 * the data source
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseCodeAndStudentId(String courseCode, Long studentId);
}
