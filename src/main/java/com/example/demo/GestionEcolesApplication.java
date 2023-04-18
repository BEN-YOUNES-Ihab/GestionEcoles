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
	
	private String[] EcoleNamesList={
			"Ecole ESTIAM LYON",
			"Ecole ESTIAM PARIS",
			"Ecole ESTIAM MELUN", 
			"Ecole ESTIAM NEUILLY",
			"Ecole ESTIAM SAINT-DENIS", 
			"Ecole ESTIAM BOURGES",
			"Ecole ESTIAM METZ",
			"Ecole ESTIAM TARBES", 
			"Ecole ESTIAM VALENCE",
			"Ecole ESTIAM GENEVE",};
	private String[] EcoleAdressesList = {
			"17 Rue du Chêne, 69007 Lyon",
            "31, Rue Paul Meurice 75020 Paris",
            "8 Avenue de la Gare, 69002 MELUN",
            "22 Rue de la Fontaine, 44000 NEUILLY",
            "5 Place de la République, 13001 SAINT-DENIS",
            "11 Rue de la Liberté, 67000 BOURGES",
            "3 Rue de la Paix, 06000 METZ",
            "16 Rue de la Victoire, 59000 TARBES",
            "9 Avenue du Général de Gaulle, 75008 VALENCE",
            "7 Rue de la Mairie, 33000 GENEVE"};

	
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
		int nombre_ecoles = random.nextInt(6) + 5;
		System.out.println(nombre_ecoles + " Ecoles");
		for (int i = 0; i < nombre_ecoles; i++) {
		    Ecole ecole = new Ecole();
		    ecole.setAdresse(EcoleAdressesList[i]);
		    ecole.setNom(EcoleNamesList[i]);
		    ecoleRepository.save(ecole); 

		    int nombre_classes = random.nextInt(81) + 20;
		    System.out.println(nombre_classes +" classes pour l'école n°" + (i+1) );
		    List<Classe> ListIds = new ArrayList<>();

		    for (int j = 0; j < nombre_classes; j++) {
		        Classe classe = new Classe();
		        int nombre_eleves = random.nextInt(21) + 10;
		        classe.setNbreleve(nombre_eleves);
		        classe.setNom("Classe n°" + (j+1));

		        classe.setEcole(ecole);
		        classeRepository.save(classe); 

		    }

		    ecole.setClasses(ListIds); 
		    ecoleRepository.save(ecole); 
		}
	}

}
