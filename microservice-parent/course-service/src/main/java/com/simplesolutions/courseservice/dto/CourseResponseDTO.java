package com.simplesolutions.courseservice.dto;

import com.simplesolutions.courseservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Class for Course response
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    private Long id;
    private String courseName;
    private String courseCode;
    private Integer credit;
    private Status status;
}
