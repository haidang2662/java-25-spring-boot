package com.example.basicauthenticationmanagelibrary.model.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    Long id;

    String username;

    Set<RoleResponse> roles;

    String email;

    String phone;

    String address;


}
