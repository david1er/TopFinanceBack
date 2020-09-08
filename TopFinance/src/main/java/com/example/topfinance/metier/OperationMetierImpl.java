package com.example.topfinance.metier;

import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.dao.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationMetierImpl implements OperationMetier{

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	
	@Override
	@Transactional
	public boolean verser(String code, double montant) {
		/*Compte cp=compteRepository.findCompte(code);
		
		if (cp==null) throw new RuntimeException("Compte intouvable");
		Operation o=new Versement();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()+montant);
		//cp.setSolde(calculSolde(code));*/
		return true;
	}
	
	@Override
	@Transactional
	public double calculSolde(String code) {
		/*List<Operation> nbRetraits;
		List<Operation> nbVersements;
		double nbRetrait=0.0,nbVersement=0.0;
		//compter le nombre d'enregistrement
		nbRetraits=operationRepository.compterRetrait(code);
		nbVersements=operationRepository.compterVersement(code);
		
		if	(nbRetraits!=null) {
			//nbRetrait=operationRepository.calculRetrait(code);
			
		} else nbRetrait=0.0;
		
		if	(nbVersements==null) {
			nbVersement=0.0;
		} else nbVersement=operationRepository.calculVersement(code);*/
		
		//return operationRepository.calculVersement(code)-operationRepository.calculRetrait(code);
	//return nbVersement-nbRetrait;
		return 0.0;
	}

	@Override
	@Transactional
	public boolean retirer(String code, double montant) {
		/*Compte cp=compteRepository.findCompte(code);
		if (cp==null) throw new RuntimeException("Compte intouvable");
		if (cp.getSolde()<montant) throw new RuntimeException("Solde Insuffisant");
		Operation o=new Retrait();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()-montant);*/
		return true;
	}

	@Override
	@Transactional
	public boolean virement(String cpte1, String cpte2, double montant) {
		retirer(cpte1, montant);
		verser(cpte2, montant);
		return true;
	}

}
