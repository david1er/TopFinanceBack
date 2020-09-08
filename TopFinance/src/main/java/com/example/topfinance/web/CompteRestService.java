package com.example.topfinance.web;

import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.dao.OperationRepository;
import com.example.topfinance.entities.Compte;
import com.example.topfinance.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CompteRestService {

	@Autowired
	private CompteMetier compteMetier;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@RequestMapping(value="/comptes",method=RequestMethod.POST)
	public Compte saveCompte(@RequestBody Compte cp) {

		return compteMetier.saveCompte(cp);
	}

	@RequestMapping(value="/comptes/{code}",method=RequestMethod.GET)
	public Compte getCompte(@PathVariable String code) {
		return compteRepository.findCompte(code);
		//return operationRepository.calculVersement(code);

	}
	
	@RequestMapping(value="/comptes",method=RequestMethod.GET)
	public List<Compte> getAllCompte() {

		return compteRepository.findAll();
	}

	@RequestMapping(value="/comptesEp",method=RequestMethod.GET)
	public List<Compte> getAllCompteEp() {

		return compteRepository.situationCpte();
	}

	//Recuperer les comptes Epargnes actifs
	@RequestMapping(value="/comptesEpActif",method=RequestMethod.GET)
	public List<Compte> getAllCompteEpActif() {

		return compteRepository.situationCpteEpActif();
	}
	

}
