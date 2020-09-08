package com.example.topfinance.metier;

import com.example.topfinance.dao.ClientRepository;
import com.example.topfinance.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//considere la classe comme un bean spring
@Service
public class ClientMetierImp implements ClientMetier{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client saveClient(Client c) {
		// TODO Auto-generated method stub
		return clientRepository.save(c);
	}

	@Override
	public List<Client> listClient() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

}
