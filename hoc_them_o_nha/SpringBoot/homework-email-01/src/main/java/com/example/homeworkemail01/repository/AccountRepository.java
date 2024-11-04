package com.example.homeworkemail01.repository;

import com.example.homeworkemail01.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
