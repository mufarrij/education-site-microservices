package com.simplesolutions.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Class for Student response
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String fullNameEnglish;
    private String fullNameArabic;
    private String email;
    private String telephone;
    private String address;
}
