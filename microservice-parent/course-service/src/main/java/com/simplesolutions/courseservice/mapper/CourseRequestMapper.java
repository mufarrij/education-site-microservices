package com.simplesolutions.courseservice.mapper;

import com.simplesolutions.courseservice.dto.CourseRequestDTO;
import com.simplesolutions.courseservice.model.Course;

/**
 * Mapper class to map {@link CourseRequestDTO}
 * to {@link Course}
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
public class CourseRequestMapper extends Mapper<CourseRequestDTO, Course> {

    @Override
    public Course map(CourseRequestDTO courseRequestDTO) {
        return Course.builder()
                .courseName(courseRequestDTO.getCourseName())
                .courseCode(courseRequestDTO.getCourseCode())
                .credit(courseRequestDTO.getCredit())
                .status(courseRequestDTO.getStatus())
                .build();
    }
}
