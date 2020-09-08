package com.example.topfinance.dao;

import com.example.topfinance.entities.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository extends JpaRepository <Pret, Long> {

    @Query(value = "select * from Pret p where p.numero_pret like :x", nativeQuery = true)
    public Pret findPret(@Param("x") String code);
}
