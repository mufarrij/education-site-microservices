package com.simplesolutions.courseservice.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplesolutions.courseservice.dto.CourseRequestDTO;
import com.simplesolutions.courseservice.dto.CourseResponseDTO;
import com.simplesolutions.courseservice.model.Status;
import com.simplesolutions.courseservice.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller class to handle Course REST API requests
 *
 * @author Mufarrij
 */
@Tag(name = "Course", description = "The Course API, contain all the operation that can be performed on course")
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Save a new course with the given detail")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        return new ResponseEntity<>(courseService.createCourse(courseRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update the details of a specific course")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable(value = "id") Long id, @RequestBody @Valid CourseRequestDTO courseRequestDTO) throws Exception {
        return new ResponseEntity<>(courseService.updateCourse(id, courseRequestDTO), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a specific course by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable(value = "id", required = false) Long id) {

        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve list of existing courses filtered by attributes")
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<List<CourseResponseDTO>> getCourses(@RequestParam(value = "courseCode", defaultValue = "", required = false) List<String> courseCode,
                                                              @RequestParam(value = "status", defaultValue = "", required = false) List<Status> status) {

        return new ResponseEntity<>(courseService.findCoursesByCodeAndStatus(courseCode, status), HttpStatus.OK);
    }

    @Operation(summary = "Delete specific course with the given ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCourse(@PathVariable(value = "id", required = false) Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }
}
