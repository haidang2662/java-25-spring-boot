package com.example.basicauthenticationmanagelibrary.config;

import com.example.basicauthenticationmanagelibrary.entity.Role;
import com.example.basicauthenticationmanagelibrary.entity.User;
import com.example.basicauthenticationmanagelibrary.reposiotry.RoleRepository;
import com.example.basicauthenticationmanagelibrary.reposiotry.UserRepository;
import com.example.basicauthenticationmanagelibrary.statics.Roles;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseInitializer implements CommandLineRunner {

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role userRole = Role.builder().name(Roles.USER.toString()).build();
        Role adminRole = Role.builder().name(Roles.ADMIN.toString()).build();
        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin123")); // Encrypt the password
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
