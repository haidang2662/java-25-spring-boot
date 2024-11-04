package com.example.manage_student_swagger.model.response;

import com.example.manage_student_swagger.entity.Student;
import com.example.manage_student_swagger.entity.Subject;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinalScoreResponse {
    Long id;

    StudentResponse studentResponse;

    SubjectResponse subjectResponse;

    Float scoreSubject;

    LocalDate examDay;

    Integer examTimes;
}
