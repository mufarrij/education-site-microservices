package com.simplesolutions.studentservice.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplesolutions.studentservice.dto.CourseDTO;
import com.simplesolutions.studentservice.security.TokenGeneratorUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class which expose methods to communicate with course-service
 * APIs
 *
 * @author Mufarrij
 * @since 24/7/2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    @Value("${course-service.port}")
    private int courseServicePort;
    @Value("${app.admin.username}")
    private String adminUser;
    @Value("${app.admin.password}")
    private String adminPassword;
    @Value("${app.base-url.path}")
    private String basePath;

    private final RestTemplate restTemplate;
    private final TokenGeneratorUtil tokenGeneratorUtil;

    public List<CourseDTO> getAvailableCoursesByCourseCode(List<String> courseCode) {
        String apiBasePath = basePath.concat(String.valueOf(courseServicePort)).concat("/api/v1");
        String getCoursesUrl = apiBasePath.concat("/courses?courseCode={courseCode}&status={status}");

        Map<String, String> apiRequestParams = new HashMap<>() {{
            put("status", "AVAILABLE");
            put("courseCode", String.join(",", courseCode));
        }};

        ResponseEntity<List<CourseDTO>> courses = restTemplate.exchange(getCoursesUrl, HttpMethod.GET,
                new HttpEntity<>(null, getJwtHeader()), new ParameterizedTypeReference<List<CourseDTO>>() {
                }, apiRequestParams);

        return courses.getBody();
    }

    public boolean isCourseAvailable(String courseCode) {
        return getAvailableCoursesByCourseCode(Collections.singletonList(courseCode)).size() > 0;
    }

    private HttpHeaders getJwtHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(tokenGeneratorUtil.getToken(adminUser, adminPassword)));
        return headers;
    }
}
