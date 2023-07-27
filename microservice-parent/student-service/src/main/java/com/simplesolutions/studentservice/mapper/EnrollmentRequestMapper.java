package com.simplesolutions.studentservice.mapper;

import com.simplesolutions.studentservice.dto.EnrollmentRequestDTO;
import com.simplesolutions.studentservice.model.Enrollment;
import com.simplesolutions.studentservice.model.Student;
import com.simplesolutions.studentservice.service.StudentService;

import lombok.RequiredArgsConstructor;

/**
 * @author Mufarrij
 * @since 24/7/2023
 */
@RequiredArgsConstructor
public class EnrollmentRequestMapper extends Mapper<EnrollmentRequestDTO, Enrollment> {

    private final StudentService studentService;

    @Override
    public Enrollment map(EnrollmentRequestDTO entity) {
        Student student = studentService.validateStudent(entity.getStudentId());
        return Enrollment.builder().courseCode(entity.getCourseCode()).student(student).build();
    }
}
