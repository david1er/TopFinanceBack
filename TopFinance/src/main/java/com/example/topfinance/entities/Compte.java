package com.example.topfinance.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compte implements Serializable{
	@Id
	@Column(name="code_compte", length=8)
	private String code_compte;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date_creation;
	private double solde;
	private String beneficiaire;
	private String contact_beneficiaire;
	private String commentaire;
	private boolean compte_operationnel;
	private String type_compte;
	
	@ManyToOne
	@JoinColumn(name="CODE_ClI")
	@JsonProperty("client")
	private Client client;

	public Compte(String code_compte) {
		super();
		this.code_compte = code_compte;
	}

	public String getCode_compte() {
		return code_compte;
	}

	public void setCode_compte(String code_compte) {
		this.code_compte = code_compte;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getType_compte() {
		return type_compte;
	}

	public void setType_compte(String type_compte) {
		this.type_compte = type_compte;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
