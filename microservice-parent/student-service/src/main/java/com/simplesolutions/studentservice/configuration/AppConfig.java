package com.simplesolutions.studentservice.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.simplesolutions.studentservice.mapper.EnrollmentRequestMapper;
import com.simplesolutions.studentservice.mapper.StudentRequestMapper;
import com.simplesolutions.studentservice.mapper.StudentResponseMapper;
import com.simplesolutions.studentservice.mapper.UserMapper;
import com.simplesolutions.studentservice.mapper.UserPrincipleMapper;
import com.simplesolutions.studentservice.security.TokenGeneratorUtil;
import com.simplesolutions.studentservice.service.StudentService;

/**
 * Bean definitions related to app config
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Configuration
public class AppConfig {

    @Bean
    public StudentRequestMapper getCourseRequestMapper() {
        return new StudentRequestMapper();
    }

    @Bean
    public StudentResponseMapper getCourseResponseMapper() {
        return new StudentResponseMapper();
    }

    @Bean
    public UserMapper getUserMapper() {
        return new UserMapper();
    }

    @Bean
    public UserPrincipleMapper getUserPrincipleMapper() {
        return new UserPrincipleMapper();
    }

    @Bean
    public EnrollmentRequestMapper getEnrollmentRequestMapper(@Qualifier("studentService") StudentService studentService) {
        return new EnrollmentRequestMapper(studentService);
    }

    @Bean
    public TokenGeneratorUtil getTokenGenerator(@Qualifier("restTemplate") RestTemplate restTemplate) {
        return new TokenGeneratorUtil(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
