package com.simplesolutions.studentservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.simplesolutions.studentservice.dto.EnrollmentRequestDTO;
import com.simplesolutions.studentservice.exception.ValidationException;
import com.simplesolutions.studentservice.mapper.EnrollmentRequestMapper;
import com.simplesolutions.studentservice.model.Student;
import com.simplesolutions.studentservice.repository.EnrollmentRepository;
import com.simplesolutions.studentservice.security.TokenGeneratorUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class to hold business logic related {@link Student} course enrollment
 * related operations
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentService {
    @Value("${course-service.port}")
    private int courseServicePort;
    @Value("${app.admin.username}")
    private String adminUser;
    @Value("${app.admin.password}")
    private String adminPassword;
    @Value("${app.base-url.path}")
    private String basePath;

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentRequestMapper enrollmentRequestMapper;
    private final CourseService courseService;
    private final RestTemplate restTemplate;
    private final TokenGeneratorUtil tokenGeneratorUtil;

    @Transactional
    public void enrollStudent(EnrollmentRequestDTO enrollmentRequest) {
        validateEnrollment(enrollmentRequest);
        enrollmentRepository.save(enrollmentRequestMapper.map(enrollmentRequest));
        log.info("Student with id: {} successfully enrolled with course : {}",
                enrollmentRequest.getStudentId(), enrollmentRequest.getCourseCode());
    }

    public boolean isEnrolled(String courseCode) {
        return enrollmentRepository.findByCourseCode(courseCode).size() > 0;
    }

    private void validateEnrollment(EnrollmentRequestDTO enrollment) {

        if (courseService.isCourseAvailable(enrollment.getCourseCode())) {
            if (isStudentAlreadyEnrolled(enrollment)) {
                throw new ValidationException(
                        String.format("Student with id : %s already registered for courseCode: %s",
                                enrollment.getStudentId(), enrollment.getCourseCode()));
            }
        } else {
            throw new ValidationException(String.format("Course with courseCode: %s is exist or available",
                    enrollment.getCourseCode()));
        }
    }

    private boolean isStudentAlreadyEnrolled(EnrollmentRequestDTO enrollment) {
        return !enrollmentRepository.findByCourseCodeAndStudentId(enrollment.getCourseCode(), enrollment.getStudentId())
                .isEmpty();
    }
}
