package com.example.adminweb.constant;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TelephoneStatus {

    ACTIVE("Đang được bán"),
    INACTIVE("Không được bán");

    public String value;
}
