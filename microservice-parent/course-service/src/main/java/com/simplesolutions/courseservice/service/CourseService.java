package com.simplesolutions.courseservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplesolutions.courseservice.dto.CourseRequestDTO;
import com.simplesolutions.courseservice.dto.CourseResponseDTO;
import com.simplesolutions.courseservice.exception.ResourceNotFoundException;
import com.simplesolutions.courseservice.exception.ValidationException;
import com.simplesolutions.courseservice.mapper.CourseRequestMapper;
import com.simplesolutions.courseservice.mapper.CourseResponseMapper;
import com.simplesolutions.courseservice.model.Course;
import com.simplesolutions.courseservice.model.Status;
import com.simplesolutions.courseservice.repository.CourseRepository;

import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class to hold business logic related {@link Course}
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final CourseRequestMapper courseRequestMapper;
    private final CourseResponseMapper courseResponseMapper;

    @Transactional
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        courseRepository.findByCourseCode(courseRequestDTO.getCourseCode()).ifPresent(x-> {
            throw new ValidationException(String.format("Course with codeCode: %s already exists",
                    courseRequestDTO.getCourseCode()));
        });
        Course course = courseRepository.save(courseRequestMapper.map(courseRequestDTO));
        log.info("Course with id: {} successfully saved.", course.getId());
        return courseResponseMapper.map(course);
    }

    @Transactional
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
        Course course = findCourseById(id);
        course.setCourseCode(courseRequestDTO.getCourseCode());
        course.setCourseName(courseRequestDTO.getCourseName());
        course.setCredit(courseRequestDTO.getCredit());
        course.setStatus(courseRequestDTO.getStatus());

        return courseResponseMapper.map(courseRepository.save(course));
    }

    @Transactional
    public void deleteCourse(Long id) {
        validateDeletion(id);
        courseRepository.deleteById(id);
        log.info("Course with id: {} successfully deleted", id);
    }

    public List<CourseResponseDTO> findCoursesByCodeAndStatus(List<String> courseCode, List<Status> status) {

        if(!Collections.isEmpty(courseCode) && !Collections.isEmpty(status))
        {
            return courseResponseMapper.map(courseRepository.findByCourseCodeInAndStatusIn(courseCode, status));
        }
        else if(!Collections.isEmpty(courseCode))
        {
            return courseResponseMapper.map(courseRepository.findByCourseCodeIn(courseCode));
        }
        else if(!Collections.isEmpty(status))
        {
            return courseResponseMapper.map(courseRepository.findByStatusIn(status));
        }
        else {
            return courseResponseMapper.map(courseRepository.findAll());
        }
    }

    public CourseResponseDTO getCourseById(Long id) {
        return courseResponseMapper.map(findCourseById(id));
    }

    private void validateDeletion(Long id) {
        Course course = findCourseById(id);
        if (studentService.isEnrollmentsExist(course.getCourseCode())) {
            throw new ValidationException(String.format("Course with given id: %d can't be deleted as it " +
                    "has ongoing enrollments", id));
        }
    }

    private Course findCourseById(Long id) {
        return courseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(String.format("Course with id: %d not found", id)));
    }

}
