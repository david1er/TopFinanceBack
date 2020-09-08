package com.example.topfinance.web;

import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.dao.PretRepository;
import com.example.topfinance.dao.RembRepository;
import com.example.topfinance.entities.Pret;
import com.example.topfinance.entities.Remboursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RembRestService {

	@Autowired
	private PretRepository pretRepository;
	
	@Autowired
	private RembRepository rembRepository;

	@Autowired
	private CompteRepository compteRepository;

	@RequestMapping(value="/remboursements/{code}", method=RequestMethod.GET)
	public List<Remboursement> saveRemb1(@PathVariable String code) {
		return rembRepository.selectRemb(code);
	}

	@RequestMapping(value="/remboursements/{code}/{montant}", method=RequestMethod.POST)
	public boolean saveRemb(
			@PathVariable String code,
			@PathVariable double montant,
			@RequestBody Remboursement r) {

		//List<Remboursement> rb=rembRepository.selectRemb(code);
		Remboursement lastRemb=rembRepository.findLastRemb(code);
		Pret pr=pretRepository.findPret(code);
		Remboursement remb=new Remboursement();

		int nombre=rembRepository.countRemb(code);
		if ((pr.getReste()-montant)<0) throw new RuntimeException("Revoir le montant!! Il se peut que le montant dépasse le reste à payer");

		if (nombre==0){

			remb.setReste_a_remb(pr.getMontant_total()-montant);
			remb.setTotal_remb(montant);
		}else {
			remb.setReste_a_remb(lastRemb.getReste_a_remb()-montant);
			remb.setTotal_remb(lastRemb.getTotal_remb()+montant);
		}
/*
		if (pr==null) throw new RuntimeException("Pret intouvable");

		// Attribution de reste a payer pour le premier remboursement
		if (rb==null) {
			r.setReste_a_remb(pr.getMontant_total());
			r.setTotal_remb(montant);

		}else if (rb!=null) {
			r.setReste_a_remb(pr.getReste());
		//	remb.setTotal_remb(lastRemb.getTotal_remb()+montant);
		}*/



		remb.setPret(pr);
		remb.setDate_remb(r.getDate_remb());
		remb.setEmploye(r.getEmploye());
		remb.setMode(r.getMode());
		remb.setRemettant(r.getRemettant());
		remb.setMontant_remb(montant);

		//Mise a jour du reste a payer
		pr.setReste(remb.getReste_a_remb());
		pretRepository.save(pr);



		//remb.setReste_a_remb(r.getReste_a_remb()-montant);
		//remb.setTotal_remb(r.getTotal_remb());

		rembRepository.save(remb);

		return true;
	}

	/*@RequestMapping(value="/remboursements/{code}", method=RequestMethod.POST)
	public boolean verser(
			@PathVariable String code,
			@RequestBody Pret p) {

		Compte cp=compteRepository.findCompte(code);
		if (cp==null) throw new RuntimeException("Compte intouvable");

		Pret pr=new Pret();
		pr.setCompte(cp);
		p.setCompte(cp);


		pretRepository.save(p);

		return true;
	}

	@RequestMapping(value="/remboursements", method=RequestMethod.GET)
	public List<Pret> getPrets() {
		return pretRepository.findAll();
	}

	@RequestMapping(value="remboursements/{code_reglement}", method=RequestMethod.GET)
	public Optional<Pret> getPret(@PathVariable Long code_reglement) {
		return pretRepository.findById(code_reglement);
	}

	@RequestMapping(value="remboursements/{code_reglement}", method=RequestMethod.DELETE)
	public void deletePret(@PathVariable Long code_reglement) {
		pretRepository.deleteById(code_reglement);
	}

	@RequestMapping(value="remboursements/{numero_pret}", method=RequestMethod.PUT)
	public Pret editPret(@RequestBody Pret m, @PathVariable Long numero_pret) {
		m.setNumero_pret(numero_pret);
		return pretRepository.save(m);
	}*/

}
