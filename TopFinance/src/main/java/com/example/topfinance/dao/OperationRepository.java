package com.example.topfinance.dao;

import com.example.topfinance.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long>{
	
	@Query(value = "select * from Operation o where o.code_cpte=:x and type_operation='V'", nativeQuery = true)
	public List<Operation>  findVersement(@Param("x") String code);
	
	@Query(value = "select * from Operation o where o.code_cpte=:x and type_operation='R'", nativeQuery = true)
	public List<Operation>  findRetrait(@Param("x") String code);

	@Query(value = "select * from Operation o where o.code_cpte=:x", nativeQuery = true)
	public List<Operation>  findOperation(@Param("x") String code);
	
	@Query(value = "select SUM(montant) from Operation o where o.code_cpte=:x and type_operation='V'", nativeQuery = true)
	public double calculVersement(@Param("x") String code);
	
	@Query(value = "select SUM(montant) from Operation o where o.code_cpte=:x and type_operation='R'", nativeQuery = true)
	public double calculRetrait(@Param("x") String code);
	
	@Query(value = "select * from Operation o where o.code_cpte=:x and type_operation='R'", nativeQuery = true)
	public List<Operation> compterRetrait(@Param("x") String code);
	
	@Query(value = "select * from Operation o where o.code_cpte=:x and type_operation='V'", nativeQuery = true)
	public List<Operation> compterVersement(@Param("x") String code);
	
	@Query(value = "select * from Operation o where o.numero_operation=:x", nativeQuery = true)
	public Operation findOperation(@Param("x") Long numeroOperation);

	@Query(value = "select * from Operation o where date_valeur>=:b and date_valeur<=:c", nativeQuery = true)
	public List<Operation> recherche(@Param("b") String dateValDeb, @Param("c") String dateValFin);

	@Query(value = "select * from Operation o where date_operation>=:z and date_operation<=:a and date_valeur>=:b and date_valeur<=:c", nativeQuery = true)
	public List<Operation> recherche2(@Param("z") String dateOpDeb, @Param("a") String dateOpFin,@Param("b") String dateValDeb, @Param("c") String dateValFin);

	@Query(value = "select * from Operation o where o.employe=:x and type_operation=:y and date_operation>=:z and date_operation<=:a and date_valeur>=:b and date_valeur<=:c", nativeQuery = true)
	public List<Operation> recherche1Last(@Param("x") String code, @Param("y") String type_operation, @Param("z") String dateOpDeb, @Param("a") String dateOpFin, @Param("b") String dateValDeb, @Param("c") String dateValFin);

	@Query(value = "select * from Operation o order by numero_operation DESC LIMIT 1", nativeQuery = true)
	public Operation findLastOpe();


	//Pour les recherche multicritères

//1
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche1(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//2
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche2(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//3
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche3(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//4
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche4(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//5
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche5(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//6
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche6(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

//7
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche7(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//8
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche8(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//9
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche9(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//10
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche10(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//11
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche11(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//12
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche12(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//13
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche13(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//14
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche14(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("e") String montant,@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//15
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche15(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//16
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche16(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//17
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche17(@Param("a") String libelle_operation, @Param("b") String employe, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//18
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche18(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//19
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche19(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//20
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche20(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//21
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.type_operation=:d and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche21(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//22
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.type_operation=:d and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche22(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("d") String type_operation, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//23
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche23(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("e") String montant, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//24
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche24(@Param("a") String libelle_operation, @Param("b") String employe,@Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//25
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche25(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("f") String mode, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//26
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche26(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//27
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche27(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//28
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.type_operation=:d and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche28(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("d") String type_operation,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//29
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and o.type_operation=:d and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche29(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//30
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b " +
			"and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche30(@Param("a") String libelle_operation, @Param("b") String employe,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//31
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche31(@Param("a") String libelle_operation, @Param("b") String employe, @Param("c") String code_cpte,
							   @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//32
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche32(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//33
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche33(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//34
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche34(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//35
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche35(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//36
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche36(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//37
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche37(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//38
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.type_operation=:d and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche38(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//39
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.type_operation=:d and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche39(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//40
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche40(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//41
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche41(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//42
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche42(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//43
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche43(@Param("a") String libelle_operation,@Param("c") String code_cpte,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//44
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche44(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//45
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche45(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("e") String montant, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//46
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche46(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//47
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche47(@Param("a") String libelle_operation,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//48
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche48(@Param("a") String libelle_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//49
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche49(@Param("a") String libelle_operation,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//50
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and o.type_operation=:d and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche50(@Param("a") String libelle_operation,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//51
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche51(@Param("a") String libelle_operation,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//52
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche52(@Param("a") String libelle_operation, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//53
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche53(@Param("a") String libelle_operation,
							   @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//54
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche54(@Param("a") String libelle_operation,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//55
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche55(@Param("a") String libelle_operation,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//56
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche56(@Param("a") String libelle_operation, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//57
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% " +
			"and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche57(@Param("a") String libelle_operation,@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//58
	@Query(value = "select * from Operation o where o.libelle_operation like %:a% and code_cpte=:c " +
			"and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche58(@Param("a") String libelle_operation, @Param("c") String code_cpte,
							   @Param("f") String mode, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);


	// Pour le libelle de l'operation

	//59
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche59(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//60
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche60(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//61
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche61(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//62
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche62(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("e") String montant,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//63
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche63(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//64
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche64(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("f") String mode,
							   @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//65
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche65(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//66
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche66(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("d") String type_operation, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//67
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche67(@Param("b") String employe, @Param("c") String code_cpte,
							   @Param("e") String montant, @Param("f") String mode,
							   @Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
							   @Param("j") String dateValDeb, @Param("k") String dateValFin);

	//68
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche68(@Param("b") String employe, @Param("c") String code_cpte,
								@Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//69
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche69(@Param("b") String employe, @Param("c") String code_cpte,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//70
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche70(@Param("b") String employe, @Param("c") String code_cpte,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//71
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche71(@Param("b") String employe, @Param("c") String code_cpte,
								@Param("e") String montant, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//72
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche72(@Param("b") String employe, @Param("c") String code_cpte,
								@Param("e") String montant,@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//73
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche73(@Param("b") String employe,
								@Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//74
	@Query(value = "select * from Operation o where employe=:b " +
			"and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche74(@Param("b") String employe,
								@Param("e") String montant, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//75
	@Query(value = "select * from Operation o where employe=:b " +
			"and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche75(@Param("b") String employe, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//76
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche76(@Param("b") String employe,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//77
	@Query(value = "select * from Operation o where employe=:b " +
			"and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche77(@Param("b") String employe,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//78
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche78(@Param("b") String employe,
								@Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//79
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.type_operation=:d and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche79(@Param("b") String employe,
								@Param("d") String type_operation, @Param("e") String montant,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//80
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.type_operation=:d and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche80(@Param("b") String employe,
								@Param("d") String type_operation, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//81
	@Query(value = "select * from Operation o where employe=:b " +
			"and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche81(@Param("b") String employe,
								@Param("e") String montant, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//82
	@Query(value = "select * from Operation o where employe=:b " +
			"and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche82(@Param("b") String employe,@Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//83
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche83(@Param("b") String employe, @Param("c") String code_cpte,
								@Param("f") String mode, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//84
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche84(@Param("b") String employe,
								@Param("d") String type_operation, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//85
	@Query(value = "select * from Operation o where employe=:b " +
			"and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche85(@Param("b") String employe,
								@Param("e") String montant,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//86
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.type_operation=:d and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche86(@Param("b") String employe,
								@Param("d") String type_operation,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//87
	@Query(value = "select * from Operation o where employe=:b " +
			"and o.type_operation=:d and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche87(@Param("b") String employe,
								@Param("d") String type_operation, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//88
	@Query(value = "select * from Operation o where employe=:b " +
			"and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche88(@Param("b") String employe,
								@Param("e") String montant, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//89
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche89(@Param("b") String employe, @Param("c") String code_cpte,
								@Param("e") String montant,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//90
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche90(@Param("c") String code_cpte,
								@Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//91
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche91(@Param("c") String code_cpte,
								@Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//92
	@Query(value = "select * from Operation o where employe=:b and code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche92(@Param("c") String code_cpte,
								@Param("d") String type_operation, @Param("e") String montant,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//93
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.type_operation=:d and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche93(@Param("c") String code_cpte,
								@Param("d") String type_operation, @Param("e") String montant,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//94
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche94(@Param("c") String code_cpte,
								@Param("d") String type_operation, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//95
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche95(@Param("c") String code_cpte,
								@Param("d") String type_operation, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//96
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.type_operation=:d and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche96(@Param("c") String code_cpte,
								@Param("d") String type_operation,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//97
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.type_operation=:d and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche97(@Param("c") String code_cpte,
								@Param("d") String type_operation, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//98
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche98(@Param("c") String code_cpte,
								@Param("e") String montant, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//99
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche99(@Param("c") String code_cpte,
								@Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//100
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche100(@Param("c") String code_cpte,
								@Param("e") String montant,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//101
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche101(@Param("c") String code_cpte,
								@Param("e") String montant, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//102
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche102(@Param("c") String code_cpte,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//103
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and montant=:e and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche103(@Param("c") String code_cpte,
								@Param("e") String montant, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//104
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche104(@Param("c") String code_cpte,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//105
	@Query(value = "select * from Operation o where o.type_operation=:d and montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche105(@Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//106
	@Query(value = "select * from Operation o where montant=:e and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche106(@Param("e") String montant, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//107
	@Query(value = "select * from Operation o where o.type_operation=:d and mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche107(@Param("d") String type_operation, @Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//108
	@Query(value = "select * from Operation o where o.type_operation=:d and montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche108(@Param("d") String type_operation, @Param("e") String montant,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//109
	@Query(value = "select * from Operation o where o.type_operation=:d and montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche109(@Param("d") String type_operation, @Param("e") String montant, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//110
	@Query(value = "select * from Operation o where mode=:f and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche110(@Param("f") String mode,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//111
	@Query(value = "select * from Operation o where montant=:e and o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche111(@Param("e") String montant,
								@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//112
	@Query(value = "select * from Operation o where montant=:e and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche112(@Param("e") String montant, @Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//113
	@Query(value = "select * from Operation o where o.comptabilise=:g and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche113(@Param("g") String comptabilise, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//114
	@Query(value = "select * from Operation o where mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche114(@Param("f") String mode,
								@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//115
	@Query(value = "select * from Operation o where date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche115(@Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

	//116
	@Query(value = "select * from Operation o where code_cpte=:c " +
			"and mode=:f and date_operation>=:h " +
			"and date_operation<=:i and date_valeur>=:j and date_valeur<=:k", nativeQuery = true)
	List<Operation> recherche116(@Param("c") String code_cpte,
								@Param("f") String mode, @Param("h") String dateOpDeb, @Param("i") String dateOpFin,
								@Param("j") String dateValDeb, @Param("k") String dateValFin);

}

