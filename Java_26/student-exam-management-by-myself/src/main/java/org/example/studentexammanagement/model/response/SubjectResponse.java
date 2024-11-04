package org.example.studentexammanagement.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectResponse {

    Long id;

    String name;

    Integer credit;

}
