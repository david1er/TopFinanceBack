package com.example.topfinance.web;

import com.example.topfinance.dao.CompteOpeCourRepository;
import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.entities.Compte;
import com.example.topfinance.entities.Compte_ope_cour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CompteOpeCourRestService {

	@Autowired
	private CompteOpeCourRepository compteOpeCourRepository;
	
	@Autowired
	private CompteRepository compteRepository;

	@RequestMapping(value="/opecour", method=RequestMethod.POST)
	public Compte_ope_cour saveReglement(@RequestBody Compte_ope_cour m) {
		return compteOpeCourRepository.save(m);
	}

	@RequestMapping(value="/opecour/{code}", method=RequestMethod.POST)
	public boolean saveOpeCour(
			@PathVariable String code,
			@RequestBody Compte_ope_cour p) {

		Compte cp=compteRepository.findCompte(code);
		if (cp==null) throw new RuntimeException("Compte intouvable");

		p.setCompte(cp);

		compteOpeCourRepository.save(p);

		return true;
	}

	@RequestMapping(value="/opecour", method=RequestMethod.GET)
	public List<Compte_ope_cour> getopecour() {
		return compteOpeCourRepository.findAll();
	}

	//Affichage des Compte_ope_cour
	@RequestMapping(value="/opecour/{code}",method=RequestMethod.GET)
	public List<Compte_ope_cour> getVersement(@PathVariable String code) {
		return compteOpeCourRepository.findCompteOpeCour(code);
	}


	@RequestMapping(value="opecour1/{code_ope_cour}", method=RequestMethod.GET)
	public Optional<Compte_ope_cour> getCompteopeCour(@PathVariable Long code_ope_cour) {
		return compteOpeCourRepository.findById(code_ope_cour);
	}

	@RequestMapping(value="opecourVers/{code}", method=RequestMethod.GET)
	public List<Compte_ope_cour> getCompteopeCourV(@PathVariable String code) {
		return compteOpeCourRepository.findCompteOpeCourV(code);
	}

	@RequestMapping(value="opecourRet/{code}", method=RequestMethod.GET)
	public List<Compte_ope_cour> getCompteopeCourR(@PathVariable String code) {
		return compteOpeCourRepository.findCompteOpeCourR(code);
	}

	@RequestMapping(value="opecour/{code_ope_cour}", method=RequestMethod.DELETE)
	public void deletePret(@PathVariable Long code_ope_cour) {
		compteOpeCourRepository.deleteById(code_ope_cour);
	}

	@RequestMapping(value="opecour/{code_ope_cour}", method=RequestMethod.PUT)
	public Compte_ope_cour editPret(@RequestBody Compte_ope_cour m, @PathVariable Long code_ope_cour) {
		m.setCode_ope_cour(code_ope_cour);
		return compteOpeCourRepository.save(m);
	}

}
