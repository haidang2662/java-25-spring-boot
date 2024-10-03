package com.example.adminweb.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    ACTIVE("Đang hoạt động"),
    INACTIVE("Bị khóa");

    public String value;

}
