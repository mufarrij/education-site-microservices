package com.simplesolutions.studentservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for Course enrollment request
 *
 * @author Mufarrij
 * @since 24/7/2023
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequestDTO {
    @NotNull(message="student id must be non null")
    Long studentId;
    @NotEmpty(message="Course code must be non null or empty ")
    String courseCode;
}
