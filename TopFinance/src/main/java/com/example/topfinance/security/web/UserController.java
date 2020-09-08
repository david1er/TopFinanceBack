package com.example.topfinance.security.web;

import com.example.topfinance.entities.Remboursement;
import com.example.topfinance.security.dao.AppUserRepository;
import com.example.topfinance.security.entities.AppUser;
import com.example.topfinance.security.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AppUserRepository appUserRepository;
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
        return  accountService.saveUser(
                userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword());
    }

    @RequestMapping(value="/allUsers}", method= RequestMethod.GET)
    public List<AppUser> getAllUsers() {
        return appUserRepository.findUsers();
    }
}

@Data
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
}
