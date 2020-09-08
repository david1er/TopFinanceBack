package com.example.topfinance.dao;

import com.example.topfinance.entities.ModeReglement;
import com.example.topfinance.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReglementRepository extends JpaRepository<ModeReglement, Long>{

    @Query(value = "select * from app_user ", nativeQuery = true)
    public List<AppUser> findUsers2();
}
