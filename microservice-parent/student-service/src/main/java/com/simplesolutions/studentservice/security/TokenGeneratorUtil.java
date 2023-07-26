package com.simplesolutions.studentservice.security;

import com.simplesolutions.studentservice.dto.AuthenticationRequestDTO;
import com.simplesolutions.studentservice.dto.AuthenticationResponseDTO;
import com.simplesolutions.studentservice.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Utility class for token generation for given credentials
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@RequiredArgsConstructor
@Slf4j
public class TokenGeneratorUtil {
    @Value("${course-service.port}")
    private int courseServicePort;
    @Value("${app.base-url.path}")
    private String basePath;

    private final RestTemplate restTemplate;

    public String getToken(String username, String password) {
        String authUrl = basePath.concat(String.valueOf(courseServicePort)).concat("/api/v1/auth/login");
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
