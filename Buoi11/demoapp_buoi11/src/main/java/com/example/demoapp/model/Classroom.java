package com.example.demoapp.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Classroom {
    // Cách 1 :
//    @Autowired
//    Student student;
//
//    @Autowired
//    Teacher teacher;
//    Cách 2 : inject qua constructor
    Student student;
    Teacher teacher;

    public Classroom(Student student, @Qualifier("teacher2") Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }
}
