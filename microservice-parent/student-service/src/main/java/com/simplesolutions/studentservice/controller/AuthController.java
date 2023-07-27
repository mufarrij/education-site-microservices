package com.simplesolutions.studentservice.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplesolutions.studentservice.dto.AuthenticationRequestDTO;
import com.simplesolutions.studentservice.dto.AuthenticationResponseDTO;
import com.simplesolutions.studentservice.dto.UserRequestDTO;
import com.simplesolutions.studentservice.security.JwtProvider;
import com.simplesolutions.studentservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller class to handle Authorization related REST API requests
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Tag(name = "Authentication", description = "The Authentication API, contains login , signup etc")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;
    private final UserService userService;

    @Operation(summary = "Authenticate given user credentials and generate JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(@RequestBody @Valid AuthenticationRequestDTO authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }

    @Operation(summary = "Register a new user with provided credentials")
    @PostMapping("/signup")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return userService.addUser(userDTO);
    }
}
