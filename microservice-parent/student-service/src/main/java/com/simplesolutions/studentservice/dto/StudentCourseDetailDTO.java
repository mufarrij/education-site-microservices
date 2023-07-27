package com.simplesolutions.studentservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO which hold data regarding student-course enrollment details
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDetailDTO {
    private StudentResponseDTO student;
    private List<CourseDTO> enrolledCourses;
}
