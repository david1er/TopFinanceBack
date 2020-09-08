package com.example.topfinance.metier;

import com.example.topfinance.entities.Client;

import java.util.List;

public interface ClientMetier {
	
	public Client saveClient(Client c);
	public List<Client> listClient();

}
