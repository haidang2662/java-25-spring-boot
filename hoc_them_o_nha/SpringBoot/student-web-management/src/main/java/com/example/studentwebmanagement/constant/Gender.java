package com.example.studentwebmanagement.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("Nam", "Male"),
    FEMALE("Nữ", "Female"),
    OTHER("Khác", "Other");

    public String vnValue;
    public String enValue;
}
