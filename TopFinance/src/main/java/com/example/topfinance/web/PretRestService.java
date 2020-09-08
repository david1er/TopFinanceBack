package com.example.topfinance.web;

import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.dao.PretRepository;
import com.example.topfinance.entities.Compte;
import com.example.topfinance.entities.Pret;
import com.example.topfinance.entities.Remboursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class PretRestService {

	@Autowired
	private PretRepository pretRepository;
	
	@Autowired
	private CompteRepository compteRepository;

	@RequestMapping(value="/prets", method=RequestMethod.POST)
	public Pret saveReglement(@RequestBody Pret m) {
		return pretRepository.save(m);
	}

	@RequestMapping(value="/prets/{code}", method=RequestMethod.POST)
	public boolean verser(
			@PathVariable String code,
			@RequestBody Pret p) {

		Compte cp=compteRepository.findCompte(code);
		if (cp==null) throw new RuntimeException("Compte intouvable");

		Remboursement remb=new Remboursement();
		remb.setReste_a_remb(p.getMontant_total());
		p.setCompte(cp);


		pretRepository.save(p);

		return true;
	}

	@RequestMapping(value="/prets", method=RequestMethod.GET)
	public List<Pret> getPrets() {
		return pretRepository.findAll();
	}

	@RequestMapping(value="prets/{code_reglement}", method=RequestMethod.GET)
	public Optional<Pret> getPret(@PathVariable Long code_reglement) {
		return pretRepository.findById(code_reglement);
	}

	@RequestMapping(value="prets/{code_reglement}", method=RequestMethod.DELETE)
	public void deletePret(@PathVariable Long code_reglement) {
		pretRepository.deleteById(code_reglement);
	}

	@RequestMapping(value="prets/{numero_pret}", method=RequestMethod.PUT)
	public Pret editPret(@RequestBody Pret m, @PathVariable Long numero_pret) {
		m.setNumero_pret(numero_pret);
		return pretRepository.save(m);
	}

}
