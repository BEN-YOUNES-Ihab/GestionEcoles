package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "classes")
public class Classe {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne
	    @JoinColumn(name = "ecole_id")
	    private Ecole ecole;
		
		private String nom;
		
		private int nbreleve;

		public Classe() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Classe(Ecole ecole, String nom, int nbreleve) {
			super();
			this.ecole = ecole;
			this.nom = nom;
			this.nbreleve = nbreleve;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Ecole getEcole() {
			return ecole;
		}

		public void setEcole(Ecole ecole) {
			this.ecole = ecole;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public int getNbreleve() {
			return nbreleve;
		}

		public void setNbreleve(int nbreleve) {
			this.nbreleve = nbreleve;
		}
		
		
		
}
