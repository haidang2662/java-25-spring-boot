package org.example.jwtauthenticationspring3.model.response;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.jwtauthenticationspring3.entity.Role;
import org.example.jwtauthenticationspring3.statics.UserStatus;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long id;

    String username;

    String password;

    String email;

    String phone;

    String address;

    UserStatus status;

    Set<RoleResponse> roles;


}
