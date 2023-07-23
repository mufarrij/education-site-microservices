package com.simplesolutions.courseservice.dto;

import com.simplesolutions.courseservice.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Class for Course request
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    @NotNull(message = "Course name must be not null")
    @NotBlank(message = "Course name must be not empty")
    private String courseName;
    @NotNull(message = "Course code must be not null")
    @NotBlank(message = "Course code must be not empty")
    private String courseCode;
    @NotNull(message = "Course credit must be not null")
    private Integer credit;
    @NotNull(message = "Course credit must be not null")
    private Status status;
}

