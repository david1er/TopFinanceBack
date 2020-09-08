package com.example.topfinance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compte_ope_cour implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code_ope_cour;
	private double montant;
	private boolean comptabilise;
	private boolean actif;
	private String libelle_operation;
	private String motif;
	private String type_operation;
	@ManyToOne
	@JoinColumn(name="code_cpte")
	private Compte compte;

	@ManyToOne
	@JoinColumn(name="mode")
	private ModeReglement mode;

	public Long getCode_ope_cour() {
		return code_ope_cour;
	}

	public void setCode_ope_cour(Long code_ope_cour) {
		this.code_ope_cour = code_ope_cour;
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

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
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
