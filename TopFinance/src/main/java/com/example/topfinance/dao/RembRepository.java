package com.example.topfinance.dao;

import com.example.topfinance.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RembRepository extends JpaRepository <Remboursement, Long> {

  /*  @Query("select count(*) from Periode p where p.numero_pret like :x", nativeQuery = true)
    public Long comte(@Param("x") String code);*/

    @Query(value = "select * from Remboursement r where r.code_pret =:x", nativeQuery = true)
    public List<Remboursement> selectRemb(@Param("x") String code);

    @Query(value = "select * from Remboursement c where c.code_pret like :x order by c.numero_remb DESC LIMIT 1", nativeQuery = true)
    public Remboursement findLastRemb(@Param("x") String code);

    @Query(value = "select count(*) from Remboursement r where r.code_pret =:x", nativeQuery = true)
    public int countRemb(@Param("x") String code);
}
