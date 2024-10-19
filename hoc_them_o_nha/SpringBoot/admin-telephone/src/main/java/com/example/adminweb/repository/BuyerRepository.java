package com.example.adminweb.repository;

import com.example.adminweb.constant.BuyerStatus;
import com.example.adminweb.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    Buyer findBuyerByEmailAndPassword(String email, String password);

    Optional<Buyer> findByEmailIgnoreCase(String email);

    List<Buyer> findAll();

    List<Buyer> findByStatus(BuyerStatus status);

}
