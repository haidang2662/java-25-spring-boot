package com.example.manage_student_swagger.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectResponse {

    Long id;

    String name;

    Long totalCredits;

}
