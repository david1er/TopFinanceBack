package com.example.topfinance.web;

import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.dao.OperationRepository;
import com.example.topfinance.entities.Compte;
import com.example.topfinance.entities.Operation;
import com.example.topfinance.metier.OperationMetier;
import com.example.topfinance.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OperationRestService {
	
	@Autowired
	private OperationMetier operationMetier;
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private ReportService reportService;

	@RequestMapping(value="operation/{numero_operation}", method=RequestMethod.GET)
	public Optional<Operation> getOperation(@PathVariable Long numero_operation) {
		return operationRepository.findById(numero_operation);
	}

	@RequestMapping(value="lastOperation", method=RequestMethod.GET)
	public Operation getlastOperation() {
		return operationRepository.findLastOpe();
	}

	@RequestMapping(value="report/{format}", method=RequestMethod.GET)
	public String generetedReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return reportService.exportReport(format);
	}

	@RequestMapping(value="report2", method=RequestMethod.GET)
	public void generetedReport2() throws JRException, FileNotFoundException {
		reportService.report2();
	}


	@RequestMapping(value="/versement/{code}/{montant}", method=RequestMethod.PUT)
	public boolean verser(
			@PathVariable String code, 
			@PathVariable double montant,
			@RequestBody Operation o) {

String type="V";
		Compte cp=compteRepository.findCompte(code);
		if (cp==null) throw new RuntimeException("Compte intouvable");

		Operation op=new Operation();
		op.setCompte(cp);
		op.setDate_operation(new Date());
		op.setMontant(montant);
		op.setMotif(o.getMotif());
		op.setMode(o.getMode());
		op.setLibelle_operation(o.getLibelle_operation());

		op.setComptabilise(o.isComptabilise());
		op.setDate_valeur(o.getDate_valeur());
		op.setEmploye(o.getEmploye());
		op.setType_operation(type);
		if (o.isComptabilise()){
			cp.setSolde(cp.getSolde()+montant);
		}

		operationRepository.save(op);

		return true;
	}

	@RequestMapping(value="/retrait/{code}/{montant}", method=RequestMethod.PUT)
	public boolean retirer(
			@PathVariable String code,
			@PathVariable double montant,
			@RequestBody Operation o) {

		String type2="R";
		Compte cp=compteRepository.findCompte(code);
		if (cp==null) throw new RuntimeException("Compte intouvable");

		Operation op=new Operation();
		op.setCompte(cp);
		op.setDate_operation(new Date());
		op.setMontant(montant);
		op.setMotif(o.getMotif());
		op.setMode(o.getMode());
		op.setLibelle_operation(o.getLibelle_operation());

		op.setComptabilise(o.isComptabilise());
		op.setDate_valeur(o.getDate_valeur());
		op.setEmploye(o.getEmploye());
		op.setType_operation(type2);
		if (o.isComptabilise()){
			cp.setSolde(cp.getSolde()-montant);
		}

		operationRepository.save(op);

		return true;
	}

	@RequestMapping(value="/retrait1/{code}/{montant}", method=RequestMethod.PUT)
	public boolean retirer(
			@PathVariable String code, 
			@PathVariable double montant) {
		return operationMetier.retirer(code, montant);
	}

	@RequestMapping(value="/virement/{cpte1}/{cpte2}/{montant}", method=RequestMethod.PUT)
	public boolean virement(
			@PathVariable String cpte1, 
			@PathVariable String cpte2, 
			@PathVariable double montant,
			@RequestBody Operation o) {

		retirer(cpte1,montant,o);
		verser(cpte2,montant,o);
		return true;
	}
	
	//Affichage des versements
	@RequestMapping(value="/versement/{code}",method=RequestMethod.GET)
	public List<Operation> getVersement(@PathVariable String code) {
		return operationRepository.findVersement(code);
	}
	
	//Affichage des retraits
		@RequestMapping(value="/retrait/{code}",method=RequestMethod.GET)
		public List<Operation> getRetrait(@PathVariable String code) {
			return operationRepository.findRetrait(code);
		}

	//Affichage des operations d'un compte
	@RequestMapping(value="/operations/{code}",method=RequestMethod.GET)
	public List<Operation> getOperations(@PathVariable String code) {
		return operationRepository.findOperation(code);
	}

	//Affichage recherche operation en fonction des dates de valeurs
	@RequestMapping(value="/operations/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> rechercheOp1(
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche(dateValDeb,dateValFin);
	}

	//Affichage recherche operation en fonction des dates d'operations et de valeurs
	@RequestMapping(value="/operations/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> rechercheOp2(
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche2(dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}

	//Affichage recherche
	/*@RequestMapping(value="/recherche/{employe}/{type_operation}/{dateOpDeb}/{dateOpFin}/{dateValDeb}/{dateValFin}",method=RequestMethod.GET)
	public List<Operation> recherche(
			@PathVariable String employe,
			@PathVariable String type_operation,
			@PathVariable String dateOpDeb,
			@PathVariable String dateOpFin,
			@PathVariable String dateValDeb,
			@PathVariable String dateValFin
	) {
		return operationRepository.recherche1(employe,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
		//return operationRepository.recherche(employe,type_operation,dateOpDeb,dateOpFin,dateValDeb,dateValFin);
	}*/
	
	@RequestMapping(value="/solde/{code}",method=RequestMethod.GET)
	public List<Operation> getCompte(@PathVariable String code) {
		return operationRepository.compterVersement(code);
		
	}
	
	//Suppression d'un versement en mettant a jour le solde
	@RequestMapping(value="/versement/{numeroOperation}/{code}/{montant}",method=RequestMethod.DELETE)
	public void deleteVersement(@PathVariable Long numeroOperation,@PathVariable String code,@PathVariable double montant) {
		Compte cp=compteRepository.findCompte(code);
		cp.setSolde(cp.getSolde()-montant);
		 operationRepository.deleteById(numeroOperation);
		
	}
	
	//Suppression d'un retrait en mettant a jour le solde
		@RequestMapping(value="/retrait/{numeroOperation}/{code}/{montant}",method=RequestMethod.DELETE)
		public void deleteRetrait(@PathVariable Long numeroOperation,@PathVariable String code,@PathVariable double montant) {
			Compte cp=compteRepository.findCompte(code);
			cp.setSolde(cp.getSolde()+montant);
			 operationRepository.deleteById(numeroOperation);
			
		}
		
		//update d'un versement en mettant a jour le solde
		@RequestMapping(value="/versement/{numeroOperation}/{code}/{montant}",method=RequestMethod.PUT)
		public Operation updateVersement(@PathVariable Long numeroOperation,
				@PathVariable String code,
				@PathVariable double montant,
			    @RequestBody Operation o){
			Compte cp=compteRepository.findCompte(code);
			if (cp==null) throw new RuntimeException("Compte intouvable");
			//Operation o=new Versement();
			Operation op=operationRepository.findOperation(numeroOperation);
			//Calcul du nouveau solde=ancien-difference entre les 2 montants
			//cp.setSolde(cp.getSolde()-( op.getMontant()-montant));
			if ((o.isComptabilise())){
				cp.setSolde(cp.getSolde()-( op.getMontant()-montant));
			}

			op.setNumero_operation(numeroOperation); //setNumeroOperation(numeroOperation);
			op.setDate_operation(new Date()); //setDateOperation(new Date());
			op.setMontant(montant);
			op.setCompte(cp);
			op.setComptabilise(o.isComptabilise());
			op.setDate_valeur(o.getDate_valeur());
			op.setLibelle_operation(o.getLibelle_operation());
			op.setEmploye(o.getEmploye());
			op.setMode(o.getMode());
			op.setMotif(o.getMotif());


			 
			return operationRepository.save(op);
						
		}

	//update d'un retrait en mettant a jour le solde
	@RequestMapping(value="/retrait/{numeroOperation}/{code}/{montant}",method=RequestMethod.PUT)
	public Operation updateRetrait(@PathVariable Long numeroOperation,
									 @PathVariable String code,
									 @PathVariable double montant,
									 @RequestBody Operation o){
		Compte cp=compteRepository.findCompte(code);
		if (cp==null) throw new RuntimeException("Compte intouvable");
		//Operation o=new Versement();
		Operation op=operationRepository.findOperation(numeroOperation);
		//Calcul du nouveau solde=ancien-difference entre les 2 montants
		cp.setSolde(cp.getSolde()+( op.getMontant()-montant));

		op.setNumero_operation(numeroOperation); //setNumeroOperation(numeroOperation);
		op.setDate_operation(new Date()); //setDateOperation(new Date());
		op.setMontant(montant);
		op.setCompte(cp);
		op.setComptabilise(o.isComptabilise());
		op.setDate_valeur(o.getDate_valeur());
		op.setLibelle_operation(o.getLibelle_operation());
		op.setEmploye(o.getEmploye());
		op.setMode(o.getMode());
		op.setMotif(o.getMotif());

		return operationRepository.save(op);

	}
		/*
		//update d'un retrait en mettant a jour le solde
		@RequestMapping(value="/retrait/{numeroOperation}/{code}/{montant}",method=RequestMethod.PUT)
		public Operation updateRetrait(@PathVariable Long numeroOperation,
				@PathVariable String code,
				@PathVariable double montant) {
					
			Compte cp=compteRepository.findCompte(code);
			if (cp==null) throw new RuntimeException("Compte intouvable");
			Operation o=new Retrait();
			Operation op=operationRepository.findOperation(numeroOperation);
			//Calcul du nouveau solde=ancien-difference entre les 2 montants
			cp.setSolde(cp.getSolde()+( op.getMontant()-montant));
					
			o.setNumeroOperation(numeroOperation);
			o.setDateOperation(new Date());
			o.setMontant(montant);
			o.setCompte(cp);
					 
			return operationRepository.save(o);
								
			}*/

}
