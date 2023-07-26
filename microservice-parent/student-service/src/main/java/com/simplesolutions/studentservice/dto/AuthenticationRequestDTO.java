package com.simplesolutions.studentservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Class for Authentication request
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {
    @NotEmpty(message = "username must be non null or empty")
    @Size(max = 255, message = "exceeds max number of charactors")
    private String username;
    @NotEmpty(message = "password must be non null or empty")
    @Size(max = 255, message = "exceeds max number of charactors")
    private String password;
}
