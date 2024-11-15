package vn.techmaster.danglh.recruitmentproject.repository;


import vn.techmaster.danglh.recruitmentproject.entity.User;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndStatus(String username, AccountStatus status);

}