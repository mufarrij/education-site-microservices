package com.simplesolutions.courseservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.simplesolutions.courseservice.dto.AuthenticationRequestDTO;
import com.simplesolutions.courseservice.dto.AuthenticationResponseDTO;
import com.simplesolutions.courseservice.exception.UnAuthorizedException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for token generation for given credentials
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@RequiredArgsConstructor
@Slf4j
public class TokenGeneratorUtil {
    @Value("${student-service.port}")
    private int studentServicePort;
    @Value("${app.base-url.path}")
    private String basePath;

    private final RestTemplate restTemplate;

    public String getToken(String username, String password) {
        String authUrl = basePath.concat(String.valueOf(studentServicePort)).concat("/api/v1/auth/login");
        try {
            return restTemplate.exchange(authUrl, HttpMethod.POST, new HttpEntity<>(getAuthRequest(username, password)),
                    AuthenticationResponseDTO.class).getBody().getAccessToken();
        } catch (HttpClientErrorException ex) {
            log.info("Token generation failed for given credentials", ex);
            throw new UnAuthorizedException("Token generation failed for configured credentials " + ex.getMessage());
        }
    }

    private AuthenticationRequestDTO getAuthRequest(String username, String password) {
        return AuthenticationRequestDTO.builder().
                username(username).password(password).build();
    }
}
