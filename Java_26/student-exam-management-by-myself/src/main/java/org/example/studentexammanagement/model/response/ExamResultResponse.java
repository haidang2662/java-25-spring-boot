package org.example.studentexammanagement.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.studentexammanagement.entity.Subject;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamResultResponse {

    Long id;

    StudentResponse studentResponse;

    SubjectResponse subjectResponse;

    Float mark;

    LocalDate examDate;

    Integer examTimes;
}
