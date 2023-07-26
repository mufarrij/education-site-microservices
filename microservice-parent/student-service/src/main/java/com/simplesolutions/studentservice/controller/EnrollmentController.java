package com.simplesolutions.studentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplesolutions.studentservice.dto.EnrollmentRequestDTO;
import com.simplesolutions.studentservice.service.EnrollmentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller class to handle REST API requests related all student-course enrollment
 * related operations
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Tag(name = "Course Enrollment", description = "Contains APIs for student course enrollment related operations")
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enrollment")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> enrollStudent(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) {
        enrollmentService.enrollStudent(enrollmentRequestDTO);
        return new ResponseEntity<>("Student enrolled success", HttpStatus.OK);
    }

}
