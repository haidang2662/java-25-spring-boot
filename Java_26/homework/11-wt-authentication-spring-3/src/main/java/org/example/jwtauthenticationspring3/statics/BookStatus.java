package org.example.jwtauthenticationspring3.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatus {
    ACTIVE("Đang hoạt động"),
    INACTIVE("Khóa");
    String value;
}
