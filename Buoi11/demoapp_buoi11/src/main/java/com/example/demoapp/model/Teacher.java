package com.example.demoapp.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Teacher {

        int id ;
        String name;

        public Teacher() {
            System.out.println("Teacher constructor");
        }

}
