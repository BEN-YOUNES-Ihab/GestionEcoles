package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.ClasseRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.entities.Classe;
import com.example.demo.entities.Ecole;


@SpringBootApplication
public class GestionEcolesApplication implements CommandLineRunner{
	
	private String[] EcoleNamesList={"Ecole ESTIAM Lyon", "Ecole Centrale Paris", "HEC Paris", "ESSEC Business School", "Sciences Po", "Université Paris-Saclay", "Université Paris Dauphine", "Université Paris Nanterre"};
	private String[] EcoleAdressesList = {
			"17 Rue du Chêne, 69007 Lyon",
            "42 Boulevard des Alouettes, 31000 Toulouse",
            "8 Avenue de la Gare, 69002 Lyon",
            "22 Rue de la Fontaine, 44000 Nantes",
            "5 Place de la République, 13001 Marseille",
            "11 Rue de la Liberté, 67000 Strasbourg",
            "3 Rue de la Paix, 06000 Nice",
            "16 Rue de la Victoire, 59000 Lille",
            "9 Avenue du Général de Gaulle, 75008 Paris",
            "7 Rue de la Mairie, 33000 Bordeaux"};

	
	@Autowired
	private ClasseRepository classeRepository;
	
	@Autowired
	private EcoleRepository ecoleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionEcolesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();
		int nbr_ecoles = random.nextInt(6) + 5;
		System.out.println("On a " + nbr_ecoles + " Ecoles");
		for (int i = 0; i < nbr_ecoles; i++) {
		    Ecole monEcole = new Ecole();
		    monEcole.setAdresse(EcoleAdressesList[i]);
		    monEcole.setNom(EcoleNamesList[i]);
		    ecoleRepository.save(monEcole); 

		    int nbr_classes = random.nextInt(81) + 20;
		    List<Classe> ListIds = new ArrayList<>();

		    for (int j = 0; j < nbr_classes; j++) {
		        Classe maClasse = new Classe();
		        int nbr_eleves = random.nextInt(21) + 10;
		        maClasse.setNbreleve(nbr_eleves);
		        maClasse.setNom("Classe n°" + (j+1));

		        maClasse.setEcole(monEcole);
		        classeRepository.save(maClasse); 

		    }

		    monEcole.setClasses(ListIds); 
		    ecoleRepository.save(monEcole); 
		}
	}

}
