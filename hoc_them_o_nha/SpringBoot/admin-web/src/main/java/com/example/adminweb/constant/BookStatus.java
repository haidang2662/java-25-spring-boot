package com.example.adminweb.constant;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BookStatus {

    ACTIVE("Đang hoạt động"),
    INACTIVE("Không hoạt động");

    public String value;
}
