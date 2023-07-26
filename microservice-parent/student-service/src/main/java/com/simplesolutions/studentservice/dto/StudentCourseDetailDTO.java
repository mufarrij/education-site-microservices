package com.simplesolutions.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<String> enrolledCourses;
}
