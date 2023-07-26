package com.simplesolutions.courseservice.configuration;

import com.simplesolutions.courseservice.mapper.CourseRequestMapper;
import com.simplesolutions.courseservice.mapper.CourseResponseMapper;
import com.simplesolutions.courseservice.mapper.UserMapper;
import com.simplesolutions.courseservice.mapper.UserPrincipleMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean definitions related to app config
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Configuration
public class AppConfig {
    @Bean
    public CourseRequestMapper getCourseRequestMapper() {
        return new CourseRequestMapper();
    }

    @Bean
    public CourseResponseMapper getCourseResponseMapper() {
        return new CourseResponseMapper();
    }

    @Bean
    public UserMapper getUserMapper() {
        return new UserMapper();
    }

    @Bean
    public UserPrincipleMapper getUserPrincipleMapper() {
        return new UserPrincipleMapper();
    }
}
