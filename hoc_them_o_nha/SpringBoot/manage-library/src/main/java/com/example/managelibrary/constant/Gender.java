package com.example.managelibrary.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    Male("Nam"),
    Female("Nũ"),
    Other("Khác");
    public String value;
}
