package com.example.topfinance.web;

import com.example.topfinance.dao.ReglementRepository;
import com.example.topfinance.entities.ModeReglement;
import com.example.topfinance.security.dao.AppUserRepository;
import com.example.topfinance.security.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class UsersRestService {

	@Autowired
	private AppUserRepository appUserRepository;

	@RequestMapping(value="/allUsers", method=RequestMethod.GET)
	public List<AppUser> getAllUsers() {
		return appUserRepository.findAll();
	}

	@RequestMapping(value="/allUsers2", method=RequestMethod.GET)
	public List<AppUser> getAllUsers2() {
		return appUserRepository.findUsers();
	}

	@RequestMapping(value="user/{id}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) {
		appUserRepository.deleteById(id);
	}
}
