package com.example.adminweb.repository;

import com.example.adminweb.constant.UserStatus;
import com.example.adminweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);

    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findAll();

    List<User> findByStatus(UserStatus status);

}
