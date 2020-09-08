package com.example.topfinance.dao;

import com.example.topfinance.entities.Compte_ope_cour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompteOpeCourRepository extends JpaRepository<Compte_ope_cour, Long>{

	@Query(value = "select * from Compte_ope_cour o where o.code_cpte=:x ", nativeQuery = true)
	public List<Compte_ope_cour>  findCompteOpeCour(@Param("x") String num_compte);

	@Query(value = "select * from Compte_ope_cour o where o.code_cpte=:x and o.type_operation='V'", nativeQuery = true)
	public List<Compte_ope_cour>  findCompteOpeCourV(@Param("x") String num_compte);

	@Query(value = "select * from Compte_ope_cour o where o.code_cpte=:x and o.type_operation='R' and o.actif=1", nativeQuery = true)
	public List<Compte_ope_cour>  findCompteOpeCourR(@Param("x") String num_compte);
	}
