package com.example.adminweb.repository;

import com.example.adminweb.constant.TelephoneStatus;
import com.example.adminweb.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {

    List<Telephone> findByStatus(TelephoneStatus status);

}
