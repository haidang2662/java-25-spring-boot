package com.example.basicauthenticationmanagelibrary.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ACTIVE("Dang hoat dong"),
    INACTIVE("khong hoat dong");
    public String value;
}
