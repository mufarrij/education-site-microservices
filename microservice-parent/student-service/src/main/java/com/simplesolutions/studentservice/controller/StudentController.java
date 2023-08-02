package com.simplesolutions.studentservice.controller;

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

import com.simplesolutions.studentservice.dto.StudentCourseDetailDTO;
import com.simplesolutions.studentservice.dto.StudentRequestDTO;
import com.simplesolutions.studentservice.dto.StudentResponseDTO;
import com.simplesolutions.studentservice.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller class to handle Student REST API requests
 *
 * @author Mufarrij
 * @since 24/7/2023
 */

@Tag(name = "Student", description = "The Student API, contain all the operation that can be performed on student")
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Register a new student with the provided details")
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StudentResponseDTO> registerStudent(@RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        return new ResponseEntity<>(studentService.registerStudent(studentRequestDTO), HttpStatus.CREATED);
    }


    @Operation(summary = "Update the details of a specific student")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable(value = "id") Long id, @RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentRequestDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete specific student with the given ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);    }

    @Operation(summary = "")
    @GetMapping("/courses")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<List<StudentCourseDetailDTO>> getStudentsCourseDetail() {
        return new ResponseEntity<>(studentService.getStudentCourseDetails(), HttpStatus.OK);
    }

    @Operation(summary = "Update the courses for specific student")
    @PutMapping("/{id}/courses")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateCourses(@PathVariable(value = "id") Long id,
                                                @RequestParam(value = "courseCode", defaultValue = "", required = true) List<String> courseCode) {
        studentService.updateCourses(id, courseCode);
        return new ResponseEntity<>("Courses successfully updated", HttpStatus.OK);
    }

    @Operation(summary = "Retrieve all registered students list")
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<List<StudentResponseDTO>> getStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }
}
