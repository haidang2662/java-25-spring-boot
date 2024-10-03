package com.example.buoi18_lam_lai.repository;

import com.example.buoi18_lam_lai.entity.User;
import com.example.buoi18_lam_lai.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User , Integer> {
    List<User> findByRole(UserRole role);
}
