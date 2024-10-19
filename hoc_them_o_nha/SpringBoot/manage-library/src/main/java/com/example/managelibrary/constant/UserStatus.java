package com.example.managelibrary.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    Valid("Đang hoạt động"),
    Invalid("Không hoạt động");
    public String value;
}
