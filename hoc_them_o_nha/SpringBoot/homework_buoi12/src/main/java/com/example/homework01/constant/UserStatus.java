package com.example.homework01.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ACTIVE("Đang hoạt động"),
    INACTIVE("Bị khóa");

    public String value;

}
