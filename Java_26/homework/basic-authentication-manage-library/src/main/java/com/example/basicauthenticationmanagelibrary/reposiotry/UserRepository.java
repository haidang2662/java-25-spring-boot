package com.example.basicauthenticationmanagelibrary.reposiotry;

import com.example.basicauthenticationmanagelibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
