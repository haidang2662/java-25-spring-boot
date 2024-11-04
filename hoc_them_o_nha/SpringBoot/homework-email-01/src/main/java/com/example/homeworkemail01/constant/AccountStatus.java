package com.example.homeworkemail01.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    ACTIVE("Đang hoạt động"),
    INACTIVE("Khóa");
    String value;
}
