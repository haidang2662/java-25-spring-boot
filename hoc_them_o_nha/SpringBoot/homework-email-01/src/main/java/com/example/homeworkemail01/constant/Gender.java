package com.example.homeworkemail01.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    FEMALE("Nữ"),
    MALE("Nam"),
    OTHER("Khác");
    String value;

}
