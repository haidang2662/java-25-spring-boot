package com.example.adminweb.repository;

import com.example.adminweb.entity.TelephoneBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneBuyRepository extends JpaRepository<TelephoneBuy, Long> {

}
