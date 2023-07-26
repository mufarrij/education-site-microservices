package com.simplesolutions.courseservice.dto;

import com.simplesolutions.courseservice.model.Status;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Course name must be non null and empty")
    private String courseName;
    @NotEmpty(message = "Course code must be non null and empty")
    private String courseCode;
    @NotNull(message = "Course credit must be not null")
    private Integer credit;
    @NotNull(message = "Course status must be not null")
    private Status status;
}

