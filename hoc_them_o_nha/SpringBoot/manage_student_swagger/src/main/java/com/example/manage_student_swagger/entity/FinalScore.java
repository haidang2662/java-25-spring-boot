package com.example.manage_student_swagger.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "final_score")
public class FinalScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    Subject subject;

    Float scoreSubject;

    LocalDate examDay;

    Integer examTimes;

}
