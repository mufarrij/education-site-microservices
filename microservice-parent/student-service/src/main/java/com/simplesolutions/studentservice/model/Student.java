package com.simplesolutions.studentservice.model;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * Entity class represent persisted Student data
 *
 * @author Mufarrij
 * @since 24/7/2023
 */
@Entity
@Table(name = "t_student")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullNameEnglish;
    private String fullNameArabic;
    private String email;
    private String telephone;
    private String address;
}
