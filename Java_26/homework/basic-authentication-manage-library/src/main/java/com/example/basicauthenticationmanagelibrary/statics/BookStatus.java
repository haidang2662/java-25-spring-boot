package com.example.basicauthenticationmanagelibrary.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatus {
    ACTIVE("Dang hoat dong"),
    INACTIVE("Khong hoat dong");
    public String value;
}
