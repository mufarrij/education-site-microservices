package com.simplesolutions.courseservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Class for Authentication request
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {
    @NotNull(message = "username must be non null")
    @Size(max = 255, message = "exceeds max number of charactors")
    private String username;
    @NotNull(message = "password must be non null")
    @Size(max = 255, message = "exceeds max number of charactors")
    private String password;
}
