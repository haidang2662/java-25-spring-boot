package com.example.buoi18_lam_lai.repository;

import com.example.buoi18_lam_lai.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite , Integer> {
}
