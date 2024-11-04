package org.example.studentexammanagement.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchStudentRequest {

    String name;
    Integer pageSize;
    Integer pageIndex;
}
