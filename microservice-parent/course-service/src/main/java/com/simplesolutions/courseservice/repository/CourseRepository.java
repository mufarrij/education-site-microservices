package com.simplesolutions.courseservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplesolutions.courseservice.model.Course;
import com.simplesolutions.courseservice.model.Status;

/**
 * Repository to be used to delegate CRUD operations on {@link Course} entity against
 * the data source
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseCodeInAndStatusIn(List<String> courseCode, List<Status> status);

    List<Course> findByCourseCodeIn(List<String> courseCode);

    List<Course> findByStatusIn(List<Status> status);

    Optional<Course> findByCourseCode(String courseCode);
}
