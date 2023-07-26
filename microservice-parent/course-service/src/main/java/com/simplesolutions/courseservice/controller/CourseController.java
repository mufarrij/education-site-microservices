package com.simplesolutions.courseservice.controller;

import com.simplesolutions.courseservice.dto.CourseRequestDTO;
import com.simplesolutions.courseservice.dto.CourseResponseDTO;
import com.simplesolutions.courseservice.model.Status;
import com.simplesolutions.courseservice.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        return new ResponseEntity<>(courseService.createCourse(courseRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable(value = "id") Long id, @RequestBody @Valid CourseRequestDTO courseRequestDTO) throws Exception {
        return new ResponseEntity<>(courseService.updateCourse(id, courseRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable(value = "id", required = false) Long id) {

        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<List<CourseResponseDTO>> getCourses(@RequestParam(value = "courseCode", defaultValue = "", required = false) List<String> courseCode,
                                                              @RequestParam(value = "status", defaultValue = "", required = false) List<Status> status) {

        return new ResponseEntity<>(courseService.findCoursesByCodeAndStatus(courseCode, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCourse(@PathVariable(value = "id", required = false) Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }


//    @DeleteMapping("/{courseCode}/valid")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
//    public ResponseEntity<Boolean> isValidCourse(@PathVariable(value = "courseCode", required = true) String courseCode) {
//
//        return new ResponseEntity<>(courseService.isValidCourse(courseCode), HttpStatus.OK);
//    }
}
