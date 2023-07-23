package com.simplesolutions.courseservice.mapper;

import com.simplesolutions.courseservice.dto.CourseResponseDTO;
import com.simplesolutions.courseservice.model.Course;

/**
 * Mapper class to map {@link Course}
 * to {@link CourseResponseDTO}
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
public class CourseResponseMapper extends Mapper<Course, CourseResponseDTO> {

    @Override
    public CourseResponseDTO map(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseCode(course.getCourseCode())
                .credit(course.getCredit())
                .status(course.getStatus())
                .build();
    }
}
