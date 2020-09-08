package com.example.topfinance.metier;

import com.example.topfinance.entities.Compte;

public interface CompteMetier {

	public Compte saveCompte(Compte cp);
	public Compte getCompte(String code);
}
 