package com.example.topfinance.web;

import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.dao.OperationRepository;
import com.example.topfinance.entities.Operation;
import com.example.topfinance.metier.OperationMetier;
import com.example.topfinance.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RechercheRestService {
	
	@Autowired
	private OperationMetier operationMetier;
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private ReportService reportService;

//Recherche multi-critère
	@RequestMapping(value="/rechercheM/{choix_libelle_operation}/{libelle_operation}/{choix_motif}/{motif}/{choix_employe}/{employe}/{choix_compte}/{code_cpte}/" +
			"{choix_type_operation}/{type_operation}/{choix_montant}/"+
			"{montant}/{choix_reglement}/{mode}/{choix_comptabilise}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> rechercheM(
			@PathVariable String choix_libelle_operation,
			@PathVariable String libelle_operation,
			@PathVariable String choix_motif,
			@PathVariable String motif,
			@PathVariable String choix_employe,
			@PathVariable String employe,
			@PathVariable String choix_compte,
			@PathVariable String code_cpte,
			@PathVariable String choix_type_operation,
			@PathVariable String type_operation,
			@PathVariable String choix_montant,
			@PathVariable String montant,
			@PathVariable String choix_reglement,
			@PathVariable String mode,
			@PathVariable String choix_comptabilise,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		List<Operation> op=null;
		//1
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
		     && choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
		     && choix_comptabilise.equals("egale")){

		 op = operationRepository.recherche1(libelle_operation,employe,code_cpte,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);

		}else
			//2
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
				&& choix_comptabilise.equals("indifferent")){

			op = operationRepository.recherche2(libelle_operation,employe,code_cpte,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);

		}else
			//3
			if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
					&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
					&& choix_comptabilise.equals("egale")){

				op=operationRepository.recherche3(libelle_operation,employe,code_cpte,type_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//4
			if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
					&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
					&& choix_comptabilise.equals("indifferent")){

					op=operationRepository.recherche4(libelle_operation,employe,code_cpte,type_operation,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
				//5
				if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
						&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
						&& choix_comptabilise.equals("egale")){

					op=operationRepository.recherche5(libelle_operation,employe,code_cpte,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
					//6
					if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
							&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
							&& choix_comptabilise.equals("indifferent")){

						op=operationRepository.recherche6(libelle_operation,employe,code_cpte,type_operation,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//7
			if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
				&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche7(libelle_operation,employe,code_cpte,type_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
				//8
				if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
						&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
						&& choix_comptabilise.equals("indifferent")){

					op=operationRepository.recherche8(libelle_operation,employe,code_cpte,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
				}else
			//9
			if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
			   && choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			   && choix_comptabilise.equals("egale")){

			op=operationRepository.recherche9(libelle_operation,employe,code_cpte,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//10
			if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
				&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche10(libelle_operation,employe,code_cpte,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//11
			if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
				&& choix_comptabilise.equals("egale")){

				op=operationRepository.recherche11(libelle_operation,employe,code_cpte,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//12
			if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
				&& choix_comptabilise.equals("indifferent")){

				op=operationRepository.recherche12(libelle_operation,employe,code_cpte,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		//13
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
				&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche13(libelle_operation,employe,code_cpte,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//14
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

				op=operationRepository.recherche14(libelle_operation,employe,code_cpte,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//15
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche15(libelle_operation,employe,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//16
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche16(libelle_operation,employe,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
			}else
		//17
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche17(libelle_operation,employe,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//18
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche18(libelle_operation,employe,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//19
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche19(libelle_operation,employe,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//20
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche20(libelle_operation,employe,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//21
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche21(libelle_operation,employe,type_operation,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//22
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche22(libelle_operation,employe,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//23
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
		    && choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

		    op=operationRepository.recherche23(libelle_operation,employe,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//24
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche24(libelle_operation,employe,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//25
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche25(libelle_operation,employe,code_cpte,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//26
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche26(libelle_operation,employe,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//27
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche27(libelle_operation,employe,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//28
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche28(libelle_operation,employe,type_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//29
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche29(libelle_operation,employe,type_operation,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//30
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche30(libelle_operation,employe,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
		//31
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("egale") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche31(libelle_operation,employe,code_cpte,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//32
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche32(libelle_operation,code_cpte,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//33
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche33(libelle_operation,code_cpte,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//34
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche34(libelle_operation,code_cpte,type_operation,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//35
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche35(libelle_operation,code_cpte,type_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//36
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche36(libelle_operation,code_cpte,type_operation,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//37
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche37(libelle_operation,code_cpte,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//38
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
		&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
		&& choix_comptabilise.equals("egale")){

		op=operationRepository.recherche38(libelle_operation,code_cpte,type_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//39
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche39(libelle_operation,code_cpte,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//40
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche40(libelle_operation,code_cpte,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//41
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche41(libelle_operation,code_cpte,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//42
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche42(libelle_operation,code_cpte,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//43
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche43(libelle_operation,code_cpte,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//44
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche44(libelle_operation,code_cpte,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//45
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche45(libelle_operation,code_cpte,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//46
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche46(libelle_operation,code_cpte,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//47
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche47(libelle_operation,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//48
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche48(libelle_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//49
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche49(libelle_operation,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//50
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche50(libelle_operation,type_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//51
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche51(libelle_operation,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//52
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche52(libelle_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//53
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche53(libelle_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//54
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche54(libelle_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//55
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("egale")){

			op=operationRepository.recherche55(libelle_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//56
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche56(libelle_operation,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		else
		//57
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
			&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche57(libelle_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}
		//58
		if (choix_libelle_operation.equals("contient") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
				&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
				&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche58(libelle_operation,code_cpte,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}

		// Pour le libelle
		//59
		if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
				&& choix_comptabilise.equals("egale")){

			op = operationRepository.recherche59(employe,code_cpte,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);

		}else
			//60
			if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
					&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
					&& choix_comptabilise.equals("indifferent")){

				op = operationRepository.recherche60(employe,code_cpte,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);

			}else
				//61
				if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
						&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
						&& choix_comptabilise.equals("egale")){

					op=operationRepository.recherche61(employe,code_cpte,type_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
				}else
					//62
					if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
							&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
							&& choix_comptabilise.equals("indifferent")){

						op=operationRepository.recherche62(employe,code_cpte,type_operation,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
					}else
						//63
						if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
								&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
								&& choix_comptabilise.equals("egale")){

							op=operationRepository.recherche63(employe,code_cpte,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
						}else
							//64
							if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
									&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
									&& choix_comptabilise.equals("indifferent")){

								op=operationRepository.recherche64(employe,code_cpte,type_operation,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
							}else
								//65
								if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
										&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
										&& choix_comptabilise.equals("egale")){

									op=operationRepository.recherche65(employe,code_cpte,type_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
								}else
									//66
									if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
											&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
											&& choix_comptabilise.equals("indifferent")){

										op=operationRepository.recherche66(employe,code_cpte,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
									}else
										//67
										if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
												&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
												&& choix_comptabilise.equals("egale")){

											op=operationRepository.recherche67(employe,code_cpte,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
										}else
											//68
											if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
													&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
													&& choix_comptabilise.equals("egale")){

												op=operationRepository.recherche68(employe,code_cpte,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
											}else
												//69
												if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
														&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
														&& choix_comptabilise.equals("egale")){

													op=operationRepository.recherche69(employe,code_cpte,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
												}else
													//70
													if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
															&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
															&& choix_comptabilise.equals("indifferent")){

														op=operationRepository.recherche70(employe,code_cpte,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
													}
		//71
		if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
				&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
				&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche71(employe,code_cpte,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}else
			//72
			if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
					&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
					&& choix_comptabilise.equals("indifferent")){

				op=operationRepository.recherche72(employe,code_cpte,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
			}else
				//73
				if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
						&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
						&& choix_comptabilise.equals("egale")){

					op=operationRepository.recherche73(employe,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
				}else
					//74
					if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
							&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
							&& choix_comptabilise.equals("egale")){

						op=operationRepository.recherche74(employe,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
					}else
						//75
						if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
								&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
								&& choix_comptabilise.equals("egale")){

							op=operationRepository.recherche75(employe,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
						}else
							//76
							if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
									&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
									&& choix_comptabilise.equals("egale")){

								op=operationRepository.recherche76(employe,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
							}else
								//77
								if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
										&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
										&& choix_comptabilise.equals("indifferent")){

									op=operationRepository.recherche77(employe,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
								}else
									//78
									if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
											&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
											&& choix_comptabilise.equals("indifferent")){

										op=operationRepository.recherche78(employe,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
									}else
										//79
										if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
												&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
												&& choix_comptabilise.equals("indifferent")){

											op=operationRepository.recherche79(employe,type_operation,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
										}else
											//80
											if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
													&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
													&& choix_comptabilise.equals("indifferent")){

												op=operationRepository.recherche80(employe,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
											}else
												//81
												if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
														&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
														&& choix_comptabilise.equals("indifferent")){

													op=operationRepository.recherche81(employe,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
												}else
													//82
													if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
															&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
															&& choix_comptabilise.equals("indifferent")){

														op=operationRepository.recherche82(employe,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
													}else
														//83
														if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
																&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																&& choix_comptabilise.equals("indifferent")){

															op=operationRepository.recherche83(employe,code_cpte,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
														}else
															//84
															if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
																	&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																	&& choix_comptabilise.equals("egale")){

																op=operationRepository.recherche84(employe,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
															}else
																//85
																if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
																		&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																		&& choix_comptabilise.equals("egale")){

																	op=operationRepository.recherche85(employe,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																}else
																	//86
																	if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
																			&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
																			&& choix_comptabilise.equals("egale")){

																		op=operationRepository.recherche86(employe,type_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																	}else
																		//87
																		if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
																				&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																				&& choix_comptabilise.equals("indifferent")){

																			op=operationRepository.recherche87(employe,type_operation,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																		}else
																			//88
																			if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("indifferent")
																					&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																					&& choix_comptabilise.equals("indifferent")){

																				op=operationRepository.recherche88(employe,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																			}else
																				//89
																				if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("egale") && choix_compte.equals("egale")
																						&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																						&& choix_comptabilise.equals("egale")){

																					op=operationRepository.recherche89(employe,code_cpte,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																				}
																				else
																					//90
																					if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																							&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																							&& choix_comptabilise.equals("egale")){

																						op=operationRepository.recherche90(code_cpte,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																					}
																					else
																						//91
																						if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																								&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																								&& choix_comptabilise.equals("indifferent")){

																							op=operationRepository.recherche91(code_cpte,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																						}
																						else
																							//92
																							if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																									&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																									&& choix_comptabilise.equals("indifferent")){

																								op=operationRepository.recherche92(code_cpte,type_operation,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																							}
																							else
																								//93
																								if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																										&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																										&& choix_comptabilise.equals("egale")){

																									op=operationRepository.recherche93(code_cpte,type_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																								}
																								else
																									//94
																									if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																											&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																											&& choix_comptabilise.equals("indifferent")){

																										op=operationRepository.recherche94(code_cpte,type_operation,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																									}
																									else
																										//95
																										if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																												&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																												&& choix_comptabilise.equals("egale")){

																											op=operationRepository.recherche95(code_cpte,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																										}
																										else
																											//96
																											if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																													&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
																													&& choix_comptabilise.equals("egale")){

																												op=operationRepository.recherche96(code_cpte,type_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																											}
																											else
																												//97
																												if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																														&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
																														&& choix_comptabilise.equals("indifferent")){

																													op=operationRepository.recherche97(code_cpte,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																												}
																												else
																													//98
																													if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																															&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																															&& choix_comptabilise.equals("egale")){

																														op=operationRepository.recherche98(code_cpte,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																													}
																													else
																														//99
																														if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																																&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																																&& choix_comptabilise.equals("egale")){

																															op=operationRepository.recherche99(code_cpte,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																														}
																														else
																															//100
																															if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																																	&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																																	&& choix_comptabilise.equals("egale")){

																																op=operationRepository.recherche100(code_cpte,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																															}
																															else
																																//101
																																if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																																		&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																																		&& choix_comptabilise.equals("indifferent")){

																																	op=operationRepository.recherche101(code_cpte,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																}
																																else
																																	//102
																																	if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																																			&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
																																			&& choix_comptabilise.equals("egale")){

																																		op=operationRepository.recherche102(code_cpte,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																	}
																																	else
																																		//103
																																		if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																																				&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																																				&& choix_comptabilise.equals("indifferent")){

																																			op=operationRepository.recherche103(code_cpte,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																		}
																																		else
																																			//104
																																			if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
																																					&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
																																					&& choix_comptabilise.equals("indifferent")){

																																				op=operationRepository.recherche104(code_cpte,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																			}
																																			else
																																				//105
																																				if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																						&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																																						&& choix_comptabilise.equals("egale")){

																																					op=operationRepository.recherche105(type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																				}
																																				else
																																					//106
																																					if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																							&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																																							&& choix_comptabilise.equals("egale")){

																																						op=operationRepository.recherche106(montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																					}
																																					else
																																						//107
																																						if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																								&& choix_type_operation.equals("egale") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																																								&& choix_comptabilise.equals("egale")){

																																							op=operationRepository.recherche107(type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																						}
																																						else
																																							//108
																																							if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																									&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																																									&& choix_comptabilise.equals("egale")){

																																								op=operationRepository.recherche108(type_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																							}
																																							else
																																								//109
																																								if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																										&& choix_type_operation.equals("egale") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																																										&& choix_comptabilise.equals("indifferent")){

																																									op=operationRepository.recherche109(type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																								}
																																								else
																																									//110
																																									if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																											&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																																											&& choix_comptabilise.equals("egale")){

																																										op=operationRepository.recherche110(mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																									}
																																									else
																																										//111
																																										if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																												&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("indifferent")
																																												&& choix_comptabilise.equals("egale")){

																																											op=operationRepository.recherche111(montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																										}
																																										else
																																											//112
																																											if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																													&& choix_type_operation.equals("indifferent") && choix_montant.equals("egale") && choix_reglement.equals("egale")
																																													&& choix_comptabilise.equals("indifferent")){

																																												op=operationRepository.recherche112(montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																											}
																																											else
																																												//113
																																												if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																														&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
																																														&& choix_comptabilise.equals("egale")){

																																													op=operationRepository.recherche113(comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																												}
																																												else
																																													//114
																																													if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																															&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
																																															&& choix_comptabilise.equals("indifferent")){

																																														op=operationRepository.recherche114(mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																													}
																																													else
																																														//115
																																														if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("indifferent")
																																																&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("indifferent")
																																																&& choix_comptabilise.equals("indifferent")){

																																															op=operationRepository.recherche115(dateOpDeb,dateOpFin,dateValDeb,dateValFin);
																																														}
		//116
		if (choix_libelle_operation.equals("indifferent") && choix_employe.equals("indifferent") && choix_compte.equals("egale")
				&& choix_type_operation.equals("indifferent") && choix_montant.equals("indifferent") && choix_reglement.equals("egale")
				&& choix_comptabilise.equals("indifferent")){

			op=operationRepository.recherche116(code_cpte,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		}

		return op;
	}


	/*//Affichage recherche 1
	@RequestMapping(value="/recherche/{libelle_operation}/{employe}/{code_cpte}/{type_operation}/" +
			"{montant}/{mode}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String type_operation,
			@PathVariable String montant,
			@PathVariable String mode,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche1(libelle_operation,employe,code_cpte,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 2
	@RequestMapping(value="/recherche2/{libelle_operation}/{employe}/{code_cpte}/{type_operation}/" +
			"{montant}/{mode}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche2(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String type_operation,
			@PathVariable String montant,
			@PathVariable String mode,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche2(libelle_operation,employe,code_cpte,type_operation,montant,mode,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 3
	@RequestMapping(value="/recherche3/{libelle_operation}/{employe}/{code_cpte}/{type_operation}/" +
			"{montant}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche3(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String type_operation,
			@PathVariable String montant,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche3(libelle_operation,employe,code_cpte,type_operation,montant,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 4
	@RequestMapping(value="/recherche4/{libelle_operation}/{employe}/{code_cpte}/{type_operation}/" +
			"{montant}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche4(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String type_operation,
			@PathVariable String montant,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche4(libelle_operation,employe,code_cpte,type_operation,montant,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 5
	@RequestMapping(value="/recherche5/{libelle_operation}/{employe}/{code_cpte}/{type_operation}/" +
			"{mode}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche5(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String type_operation,
			@PathVariable String mode,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche5(libelle_operation,employe,code_cpte,type_operation,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 6
	@RequestMapping(value="/recherche6/{libelle_operation}/{employe}/{code_cpte}/{type_operation}/" +
			"{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche6(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String type_operation,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche6(libelle_operation,employe,code_cpte,type_operation,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 7
	@RequestMapping(value="/recherche7/{libelle_operation}/{employe}/{code_cpte}/{type_operation}/" +
			"{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche7(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String type_operation,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche7(libelle_operation,employe,code_cpte,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 8
	@RequestMapping(value="/recherche8/{libelle_operation}/{employe}/{code_cpte}/" +
			"{montant}/{mode}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche8(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String montant,
			@PathVariable String mode,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche8(libelle_operation,employe,code_cpte,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 9
	@RequestMapping(value="/recherche9/{libelle_operation}/{employe}/{code_cpte}/" +
			"{mode}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche9(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String mode,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche9(libelle_operation,employe,code_cpte,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 10
	@RequestMapping(value="/recherche10/{libelle_operation}/{employe}/{code_cpte}/" +
			"{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche10(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche10(libelle_operation,employe,code_cpte,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 11
	@RequestMapping(value="/recherche11/{libelle_operation}/{employe}/{code_cpte}/" +
			"{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche11(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String code_cpte,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche11(libelle_operation,employe,code_cpte,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 12
	@RequestMapping(value="/recherche12/{libelle_operation}/{employe}/{type_operation}/" +
			"{montant}/{mode}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche12(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String type_operation,
			@PathVariable String montant,
			@PathVariable String mode,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche12(libelle_operation,employe,type_operation,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 13
	@RequestMapping(value="/recherche13/{libelle_operation}/{employe}/" +
			"{montant}/{mode}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche13(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String montant,
			@PathVariable String mode,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche13(libelle_operation,employe,montant,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 14
	@RequestMapping(value="/recherche14/{libelle_operation}/{employe}/" +
			"{mode}/{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche14(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String mode,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche14(libelle_operation,employe,mode,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 15
	@RequestMapping(value="/recherche15/{libelle_operation}/{employe}/" +
			"{comptabilise}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche15(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String comptabilise,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche15(libelle_operation,employe,comptabilise,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche 16
	@RequestMapping(value="/recherche16/{libelle_operation}/{employe}/" +
			"{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche16(
			@PathVariable String libelle_operation,
			@PathVariable String employe,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche16(libelle_operation,employe,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}
*/
}
