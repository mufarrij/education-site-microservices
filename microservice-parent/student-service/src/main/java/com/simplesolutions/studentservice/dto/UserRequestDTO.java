package com.simplesolutions.studentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Class for User request
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotNull(message = "username must be non null")
    @Size(max = 255, message = "exceeds max number of charactors")
    private String username;
    @NotNull(message = "username must be non null")
    @Size(max = 255, message = "exceeds max number of charactors")
    private String password;
    @NotNull
    @NotBlank(message="role must be defined")
    private String roles;
    private boolean enabled;
}
