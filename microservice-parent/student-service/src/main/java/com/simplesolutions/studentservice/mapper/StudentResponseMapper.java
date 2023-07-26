package com.simplesolutions.studentservice.mapper;

import com.simplesolutions.studentservice.dto.StudentResponseDTO;
import com.simplesolutions.studentservice.model.Student;

/**
 * Mapper class to map {@link Student}
 * to {@link StudentResponseDTO}
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
public class StudentResponseMapper extends Mapper<Student, StudentResponseDTO> {
    @Override
    public StudentResponseDTO map(Student student) {
        return StudentResponseDTO.builder()
                .id(student.getId())
                .fullNameEnglish(student.getFullNameEnglish())
                .fullNameArabic(student.getFullNameArabic())
                .email(student.getEmail())
                .telephone(student.getTelephone())
                .address(student.getAddress())
                .build();
    }
}
