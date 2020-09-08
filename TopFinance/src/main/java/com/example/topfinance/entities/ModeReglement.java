package com.example.topfinance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ModeReglement {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_reglement;
	private String code_reglement;
	
	private String libelle_reglement;
	private String commentaire;

	public ModeReglement(Long id_reglement) {
		super();
		this.id_reglement = id_reglement;
	}

	public Long getId_reglement() {
		return id_reglement;
	}

	public void setId_reglement(Long id_reglement) {
		this.id_reglement = id_reglement;
	}

	public String getCode_reglement() {
		return code_reglement;
	}

	public void setCode_reglement(String code_reglement) {
		this.code_reglement = code_reglement;
	}

	public String getLibelle_reglement() {
		return libelle_reglement;
	}

	public void setLibelle_reglement(String libelle_reglement) {
		this.libelle_reglement = libelle_reglement;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
}
