package com.simplesolutions.studentservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.simplesolutions.studentservice.dto.CourseDTO;
import com.simplesolutions.studentservice.dto.EnrollmentRequestDTO;
import com.simplesolutions.studentservice.exception.ResourceNotFoundException;
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
    private final RestTemplate restTemplate;
    private final TokenGeneratorUtil tokenGeneratorUtil;

    @Transactional
    public void enrollStudent(EnrollmentRequestDTO enrollmentRequest) {
        validateEnrollment(enrollmentRequest);
        enrollmentRepository.save(enrollmentRequestMapper.map(enrollmentRequest));
        log.info("Student with id: {} successfully enrolled with course : {}",
                enrollmentRequest.getStudentId(), enrollmentRequest.getCourseCode());
    }

    private void validateEnrollment(EnrollmentRequestDTO enrollment) {
        if (!isCourseAvailable(enrollment.getCourseCode())) {
            throw new ResourceNotFoundException(String.format("Course with courseCode: %s is exist or available",
                    enrollment.getCourseCode()));
        }
        if (isStudentAlreadyEnrolled(enrollment)) {
            throw new ValidationException(
                    String.format("Student with id : %s already registered for courseCode: %s",
                            enrollment.getStudentId(), enrollment.getCourseCode()));
        }
    }

    private boolean isCourseAvailable(String courseCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(tokenGeneratorUtil.getToken(adminUser, adminPassword)));

        String apiBasePath = basePath.concat(String.valueOf(courseServicePort)).concat("/api/v1");
        String getCoursesUrl = apiBasePath.concat("/courses?courseCode={courseCode}&status={status}");

        Map<String, String> apiRequestParams = new HashMap<>() {{
            put("status", "AVAILABLE");
            put("courseCode", courseCode);
        }};

        ResponseEntity<CourseDTO[]> courses = restTemplate.exchange(getCoursesUrl, HttpMethod.GET,
                new HttpEntity<>(null, headers), CourseDTO[].class, apiRequestParams);

        return courses.getBody().length > 0;
    }

    private boolean isStudentAlreadyEnrolled(EnrollmentRequestDTO enrollment) {
        return !enrollmentRepository.findByCourseCodeAndStudentId(enrollment.getCourseCode(), enrollment.getStudentId())
                .isEmpty();
    }
}
