package com.example.topfinance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client implements Serializable{
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code_client;
	private String nom_client;
	private String email_client;
	private String sexe_client;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date_nais;
	private String profession;
	private String telephone;
	private String numero_cni;
	private String photo;
	private String signature;
	private String quartier;


	@OneToMany(mappedBy="client", fetch=FetchType.LAZY, cascade= {CascadeType.ALL})
	private Collection<Compte> compte;

	public Client(Long code_client) {
		super();
		this.code_client = code_client;
	}

	public Long getCode_client() {
		return code_client;
	}

	public void setCode_client(Long code_client) {
		this.code_client = code_client;
	}

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getEmail_client() {
		return email_client;
	}

	public void setEmail_client(String email_client) {
		this.email_client = email_client;
	}

	public String getSexe_client() {
		return sexe_client;
	}

	public void setSexe_client(String sexe_client) {
		this.sexe_client = sexe_client;
	}

	public Date getDate_nais() {
		return date_nais;
	}

	public void setDate_nais(Date date_nais) {
		this.date_nais = date_nais;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNumero_cni() {
		return numero_cni;
	}

	public void setNumero_cni(String numero_cni) {
		this.numero_cni = numero_cni;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	@JsonIgnore
	public Collection<Compte> getCompte() {
		return compte;
	}

	public void setCompte(Collection<Compte> compte) {
		this.compte = compte;
	}
}
