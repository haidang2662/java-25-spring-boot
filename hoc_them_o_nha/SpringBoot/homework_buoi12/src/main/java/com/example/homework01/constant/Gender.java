package com.example.homework01.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác")
    ;

    public String stringName;
//
//    public static Gender getByName(String name) {
//        for (Gender value : Gender.values()) {
//            if (value.stringName.equalsIgnoreCase(name)) {
//                return value;
//            }
//        }
//        return null;
//    }


}
