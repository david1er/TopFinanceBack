package com.example.topfinance.dao;

import com.example.topfinance.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CompteRepository extends JpaRepository<Compte, String>{

	@Query(value = "select * from Compte c where c.code_compte like :x", nativeQuery = true)
	public Compte findCompte(@Param("x") String code);

	@Query(value = "select * from Compte c where c.type_compte='Compte Epargne'", nativeQuery = true)
	public List<Compte> situationCpte();

	@Query(value = "select * from Compte c where c.type_compte='Compte Epargne' and c.compte_operationnel=1", nativeQuery = true)
	public List<Compte> situationCpteEpActif();
	
	@Query(value = "delete * from Compte c where c.code_compte like :x", nativeQuery = true)
	public Compte deleteCompte(@Param("x") String code);
	
	
	
	//@Query(value = "select SUM(montant) from Compte c where c.code_compte like :x", nativeQuery = true)
	//public Compte calculSolde(@Param("x") String code);

	
}
