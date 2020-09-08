package com.example.topfinance.entities;

import java.io.Serializable;


public class Email implements Serializable {

    private String to;
    private String messageSubject;
    private String messageBody;


    public Email(String to, String messageSubject, String messageBody) {
        this.to = to;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
    }

    public Email() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
