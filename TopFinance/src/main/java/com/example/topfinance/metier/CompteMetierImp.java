package com.example.topfinance.metier;

import com.example.topfinance.dao.CompteRepository;
import com.example.topfinance.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompteMetierImp implements CompteMetier{

	@Autowired
	private CompteRepository compteRepository;
	
	@Override
	public Compte saveCompte(Compte cp) {

		cp.setDate_creation(new Date());
		return compteRepository.save(cp);
	}

	@Override
	public Compte getCompte(String code) {
		// TODO Auto-generated method stub
		return compteRepository.getOne(code);
	}



}
