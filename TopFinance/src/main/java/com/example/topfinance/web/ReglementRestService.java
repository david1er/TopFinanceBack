package com.example.topfinance.web;

import com.example.topfinance.dao.ReglementRepository;
import com.example.topfinance.entities.ModeReglement;
import com.example.topfinance.security.dao.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ReglementRestService {
	
	@Autowired
	private ReglementRepository reglementRepository;
	
	@RequestMapping(value="/reglements", method=RequestMethod.POST)
	public ModeReglement saveReglement(@RequestBody ModeReglement m) {
		return reglementRepository.save(m);
	}
	
	@RequestMapping(value="/reglements", method=RequestMethod.GET)
	public List<ModeReglement> getReglements() {
		return reglementRepository.findAll();
	}
	
	@RequestMapping(value="reglements/{code_reglement}", method=RequestMethod.GET)
	public Optional<ModeReglement> getReglement(@PathVariable Long code_reglement) {
		return reglementRepository.findById(code_reglement);
	}
	
	@RequestMapping(value="reglements/{code_reglement}", method=RequestMethod.DELETE)
	public void deleteReglement(@PathVariable Long code_reglement) {
		 reglementRepository.deleteById(code_reglement);
	}
	
	@RequestMapping(value="reglements/{id_reglement}", method=RequestMethod.PUT)
	public ModeReglement editReglement(@RequestBody ModeReglement m, @PathVariable Long id_reglement) {
		m.setId_reglement(id_reglement);
		return reglementRepository.save(m);
	}

}
