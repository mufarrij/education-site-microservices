package com.simplesolutions.courseservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplesolutions.courseservice.security.TokenGeneratorUtil;

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
public class StudentService {
    @Value("${student-service.port}")
    private int studentServicePort;
    @Value("${app.admin.username}")
    private String adminUser;
    @Value("${app.admin.password}")
    private String adminPassword;
    @Value("${app.base-url.path}")
    private String basePath;

    private final RestTemplate restTemplate;
    private final TokenGeneratorUtil tokenGeneratorUtil;

    public Boolean isEnrollmentsExist(String courseCode) {
        String apiBasePath = basePath.concat(String.valueOf(studentServicePort)).concat("/api/v1");
        String studentApiUrl = apiBasePath.concat("/students/isEnrolled?courseCode={courseCode}");

        Map<String, String> apiRequestParams = new HashMap<>() {{
            put("courseCode", courseCode);
        }};

        return restTemplate.exchange(studentApiUrl, HttpMethod.GET,
                new HttpEntity<>(null, getJwtHeader()), Boolean.class , apiRequestParams).getBody();
    }

    private HttpHeaders getJwtHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(tokenGeneratorUtil.getToken(adminUser, adminPassword)));
        return headers;
    }
}