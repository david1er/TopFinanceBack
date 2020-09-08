package com.example.topfinance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Remboursement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long numero_remb;
    private double montant_remb;
    private double total_remb;
    private double reste_a_remb;
    private String remettant;
    private String employe;
    private Date date_remb;

    @ManyToOne
    @JoinColumn(name="mode")
    private ModeReglement mode;

    @ManyToOne
    @JoinColumn(name="code_pret")
    private Pret pret;

    public ModeReglement getMode() {
        return mode;
    }

    public void setMode(ModeReglement mode) {
        this.mode = mode;
    }

    public Long getNumero_remb() {
        return numero_remb;
    }

    public void setNumero_remb(Long numero_remb) {
        this.numero_remb = numero_remb;
    }

    public double getMontant_remb() {
        return montant_remb;
    }

    public void setMontant_remb(double montant_remb) {
        this.montant_remb = montant_remb;
    }

    public double getTotal_remb() {
        return total_remb;
    }

    public void setTotal_remb(double total_remb) {
        this.total_remb = total_remb;
    }

    public double getReste_a_remb() {
        return reste_a_remb;
    }

    public void setReste_a_remb(double reste_a_remb) {
        this.reste_a_remb = reste_a_remb;
    }

    public String getRemettant() {
        return remettant;
    }

    public void setRemettant(String remettant) {
        this.remettant = remettant;
    }

    public Date getDate_remb() {
        return date_remb;
    }

    public void setDate_remb(Date date_remb) {
        this.date_remb = date_remb;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public String getEmploye() {
        return employe;
    }

    public void setEmploye(String employe) {
        this.employe = employe;
    }
}
