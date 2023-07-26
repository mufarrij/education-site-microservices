package com.simplesolutions.studentservice.service;

import com.simplesolutions.studentservice.dto.StudentCourseDetailDTO;
import com.simplesolutions.studentservice.dto.StudentRequestDTO;
import com.simplesolutions.studentservice.dto.StudentResponseDTO;
import com.simplesolutions.studentservice.exception.ResourceNotFoundException;
import com.simplesolutions.studentservice.mapper.StudentRequestMapper;
import com.simplesolutions.studentservice.mapper.StudentResponseMapper;
import com.simplesolutions.studentservice.model.Enrollment;
import com.simplesolutions.studentservice.model.Student;
import com.simplesolutions.studentservice.repository.EnrollmentRepository;
import com.simplesolutions.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class to hold business logic related {@link Student}
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRequestMapper studentRequestMapper;
    private final StudentResponseMapper studentResponseMapper;

    @Transactional
    public StudentResponseDTO registerStudent(StudentRequestDTO studentRequestDTO){
        Student student = studentRepository.save(studentRequestMapper.map(studentRequestDTO));
        log.info("Student with id: {} successfully saved.", student.getId());
        return studentResponseMapper.map(student);
    }

    @Transactional
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO){
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id: %d not found", id)));

        student.setFullNameEnglish(studentRequestDTO.getFullNameEnglish());
        student.setFullNameArabic(studentRequestDTO.getFullNameArabic());
        student.setEmail(studentRequestDTO.getEmail());
        student.setTelephone(studentRequestDTO.getTelephone());
        student.setAddress(student.getAddress());

        return studentResponseMapper.map(studentRepository.save(student));
    }

    @Transactional
    public void deleteStudent(Long id){
        studentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(String.format("Course with id: %d not found", id)));
        studentRepository.deleteById(id);
        log.info("Student with id: {} successfully deleted", id);
    }

    public Optional<Student> findByStudentId(Long id)
    {
        return studentRepository.findById(id);
    }

    public List<StudentCourseDetailDTO> getStudentCourseDetails() {

        List<Student> students = studentRepository.findAll();
        List<StudentCourseDetailDTO> studentCourseDetailList = new ArrayList<>();

        for (Student student: students) {
            List<String> courses = enrollmentRepository.findByStudentId(student.getId()).stream().map(Enrollment::getCourseCode)
                    .collect(Collectors.toList());
            StudentCourseDetailDTO studentCourseDetail = StudentCourseDetailDTO.builder()
                    .student(studentResponseMapper.map(student)).enrolledCourses(courses).build();
            studentCourseDetailList.add(studentCourseDetail);
        }

        return studentCourseDetailList;
    }


}
