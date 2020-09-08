package com.example.topfinance.web;

import com.example.topfinance.dao.OperationRepository;
import com.example.topfinance.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class EmailRestController {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    public OperationRepository operationRepository;

    @RequestMapping(value="/email", method= RequestMethod.GET)
    public String email(){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo("Katissieric@gmail.com");
        message.setSubject("Spring boot Applicaation");
        message.setText("Yes I can.");
        javaMailSender.send(message);

        return "Succes";
    }

    @RequestMapping(value="/sendEmail", method= RequestMethod.POST)
    public String SendEmail(@RequestBody Email email){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getMessageSubject());
        message.setText(email.getMessageBody());
        javaMailSender.send(message);

        return "Succes";
    }

    
  }
