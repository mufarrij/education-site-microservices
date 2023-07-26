package com.simplesolutions.studentservice.controller;

import com.simplesolutions.studentservice.dto.StudentCourseDetailDTO;
import com.simplesolutions.studentservice.dto.StudentRequestDTO;
import com.simplesolutions.studentservice.dto.StudentResponseDTO;
import com.simplesolutions.studentservice.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StudentResponseDTO> registerStudent(@RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        return new ResponseEntity<>(studentService.registerStudent(studentRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable(value = "id") Long id, @RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);    }

    @GetMapping("/courses")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<List<StudentCourseDetailDTO>> getStudentsCourseDetail() {
        return new ResponseEntity<>(studentService.getStudentCourseDetails(), HttpStatus.OK);
    }

}
