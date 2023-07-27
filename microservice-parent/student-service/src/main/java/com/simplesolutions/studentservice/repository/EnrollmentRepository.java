package com.simplesolutions.studentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplesolutions.studentservice.model.Enrollment;
import com.simplesolutions.studentservice.model.Student;

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
    List<Enrollment> findByCourseCode(String CourseCode);
    void deleteByStudentId(Long studentId);
}
