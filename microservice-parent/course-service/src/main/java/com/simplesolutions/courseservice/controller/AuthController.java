package com.simplesolutions.courseservice.controller;

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

import com.simplesolutions.courseservice.dto.AuthenticationRequestDTO;
import com.simplesolutions.courseservice.dto.AuthenticationResponseDTO;
import com.simplesolutions.courseservice.dto.UserRequestDTO;
import com.simplesolutions.courseservice.security.JwtProvider;
import com.simplesolutions.courseservice.service.UserService;

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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(@RequestBody @Valid AuthenticationRequestDTO authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return userService.addUser(userDTO);
    }
}
