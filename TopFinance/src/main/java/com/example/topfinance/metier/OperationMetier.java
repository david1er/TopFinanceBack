package com.example.topfinance.metier;

public interface OperationMetier {

	public boolean verser(String code, double montant);
	public boolean retirer(String code, double montant);
	public boolean virement(String cpte1, String cpte2, double montant);
	double calculSolde(String code);
}
