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
public class Pret implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long numero_pret;
    private double capital;
    private double frais_dossier;
    private double interet;
    private double mensualite;
    private double montant_total;
    private double nombre_echeance;
    private double reste;
    private Date date_effet_pret;
    private Date date_debut_remboursement;

    @ManyToOne
    @JoinColumn(name="code_cpte")
    private Compte compte;

    public Long getNumero_pret() {
        return numero_pret;
    }

    public void setNumero_pret(Long numero_pret) {
        this.numero_pret = numero_pret;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getFrais_dossier() {
        return frais_dossier;
    }

    public void setFrais_dossier(double frais_dossier) {
        this.frais_dossier = frais_dossier;
    }

    public double getInteret() {
        return interet;
    }

    public void setInteret(double interet) {
        this.interet = interet;
    }

    public double getMensualite() {
        return mensualite;
    }

    public void setMensualite(double mensualite) {
        this.mensualite = mensualite;
    }

    public double getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(double montant_total) {
        this.montant_total = montant_total;
    }

    public double getNombre_echeance() {
        return nombre_echeance;
    }

    public void setNombre_echeance(double nombre_echeance) {
        this.nombre_echeance = nombre_echeance;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public Date getDate_effet_pret() {
        return date_effet_pret;
    }

    public void setDate_effet_pret(Date date_effet_pret) {
        this.date_effet_pret = date_effet_pret;
    }

    public Date getDate_debut_remboursement() {
        return date_debut_remboursement;
    }

    public void setDate_debut_remboursement(Date date_debut_remboursement) {
        this.date_debut_remboursement = date_debut_remboursement;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
