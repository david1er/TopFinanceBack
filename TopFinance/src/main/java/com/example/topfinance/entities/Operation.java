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
public class Operation implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero_operation;
	private Date date_operation;
	private double montant;
	private boolean comptabilise;
	private Date date_valeur;
	private String libelle_operation;
	private String motif;
	private String employe;
	private String type_operation;
	@ManyToOne
	@JoinColumn(name="code_cpte")
	private Compte compte;

	@ManyToOne
	@JoinColumn(name="mode")
	private ModeReglement mode;

	public Long getNumero_operation() {
		return numero_operation;
	}

	public void setNumero_operation(Long numero_operation) {
		this.numero_operation = numero_operation;
	}

	public Date getDate_operation() {
		return date_operation;
	}

	public void setDate_operation(Date date_operation) {
		this.date_operation = date_operation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public boolean isComptabilise() {
		return comptabilise;
	}

	public void setComptabilise(boolean comptabilise) {
		this.comptabilise = comptabilise;
	}

	public Date getDate_valeur() {
		return date_valeur;
	}

	public void setDate_valeur(Date date_valeur) {
		this.date_valeur = date_valeur;
	}

	public String getLibelle_operation() {
		return libelle_operation;
	}

	public void setLibelle_operation(String libelle_operation) {
		this.libelle_operation = libelle_operation;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getEmploye() {
		return employe;
	}

	public void setEmploye(String employe) {
		this.employe = employe;
	}

	public String getType_operation() {
		return type_operation;
	}

	public void setType_operation(String type_operation) {
		this.type_operation = type_operation;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public ModeReglement getMode() {
		return mode;
	}

	public void setMode(ModeReglement mode) {
		this.mode = mode;
	}
}
