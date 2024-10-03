package com.example.homework01.repository;

import com.example.homework01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);

    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findAll();

}
