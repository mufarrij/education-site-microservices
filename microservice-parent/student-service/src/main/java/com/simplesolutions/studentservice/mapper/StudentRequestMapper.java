package com.simplesolutions.studentservice.mapper;

import com.simplesolutions.studentservice.dto.StudentRequestDTO;
import com.simplesolutions.studentservice.model.Student;

/**
 * Mapper class to map {@link StudentRequestDTO}
 * to {@link Student}
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
public class StudentRequestMapper extends Mapper<StudentRequestDTO, Student> {

    @Override
    public Student map(StudentRequestDTO courseRequestDTO) {
        return Student.builder()
                .fullNameEnglish(courseRequestDTO.getFullNameEnglish())
                .fullNameArabic(courseRequestDTO.getFullNameArabic())
                .email(courseRequestDTO.getEmail())
                .address(courseRequestDTO.getAddress())
                .telephone(courseRequestDTO.getTelephone())
                .build();
    }
}
