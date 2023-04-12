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
	
	private String[] EcoleNamesList={"Ecole ESTIAM", "Ecole Centrale Paris", "HEC Paris", "ESSEC Business School", "Sciences Po", "Université Paris-Saclay", "Université Paris Dauphine", "Université Paris Nanterre"};
	private String[] EcoleAdressesList={"Adresse 1 ","Adresse 2","Adresse 3","Adresse 4","Adresse 5","Adresse 6","Adresse 7","Adresse 8","Adresse 9","Adresse 10"};
	
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
		System.out.println("On a " + nbr_ecoles + " d'Ecole");
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
		        maClasse.setNom("Classe n°" + j);

		        maClasse.setEcole(monEcole);
		        classeRepository.save(maClasse); 

		    }

		    monEcole.setClasses(ListIds); 
		    ecoleRepository.save(monEcole); 
		}
	}

}
