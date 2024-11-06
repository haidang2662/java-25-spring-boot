package com.example.homeworkemail01.repository;

import com.example.homeworkemail01.constant.AccountStatus;
import com.example.homeworkemail01.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmailAndStatus(String email, AccountStatus status);

}
