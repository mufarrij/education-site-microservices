package com.simplesolutions.courseservice.repository;

import com.simplesolutions.courseservice.model.Course;
import com.simplesolutions.courseservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
}
