package com.simplesolutions.courseservice.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class represent persisted Course data
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Entity
@Table(name = "t_course")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String courseCode;
    private Integer credit;
    @Enumerated(EnumType.STRING)
    private Status status;
}
