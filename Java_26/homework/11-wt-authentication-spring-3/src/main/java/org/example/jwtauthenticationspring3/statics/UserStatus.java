package org.example.jwtauthenticationspring3.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    ACTIVE("Đang hoạt động"),
    INACTIVE("Khóa");
    String value;
}
