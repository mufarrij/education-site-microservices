package com.simplesolutions.studentservice.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplesolutions.studentservice.dto.EnrollmentRequestDTO;
import com.simplesolutions.studentservice.service.EnrollmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "Allocate student with selected and course and save")
    @PostMapping("/enrollment")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> enrollStudent(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) {
        enrollmentService.enrollStudent(enrollmentRequestDTO);
        return new ResponseEntity<>("Student enrolled success", HttpStatus.OK);
    }

    @Operation(summary = "Check whether enrollments exist for given course")
    @GetMapping("/isEnrolled")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<Boolean> isEnrolled(@RequestParam(value = "courseCode") String courseCode) {
        return new ResponseEntity<>(enrollmentService.isEnrolled(courseCode), HttpStatus.OK);
    }

}
