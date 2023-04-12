package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ecoles")
public class Ecole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 private String adresse;
	 
	 private String nom;
	 
   	 public Ecole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ecole(String adresse, String nom, List<Classe> classes) {
		super();
		this.adresse = adresse;
		this.nom = nom;
		this.classes = classes;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	@OneToMany(mappedBy = "ecole", cascade = CascadeType.ALL)
	 private List<Classe> classes;
	
	
	 
}
