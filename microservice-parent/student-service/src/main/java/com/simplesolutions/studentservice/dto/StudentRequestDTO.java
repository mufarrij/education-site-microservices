package com.simplesolutions.studentservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Class for Student request
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
    @NotEmpty
    @Size(min = 2, message = "English name should have at least 2 characters")
    private String fullNameEnglish;
    @NotEmpty
    @Size(min = 2, message = "Arabic name should have at least 2 characters")
    private String fullNameArabic;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String telephone;
    @NotEmpty
    private String address;
}
