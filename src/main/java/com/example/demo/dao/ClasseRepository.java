package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Classe;

public interface ClasseRepository extends JpaRepository<Classe , Long>{
	
	@Query("SELECT c FROM Classe c WHERE c.ecole.id = :ecoleId")
    List<Classe> findByEcoleId(@Param("ecoleId") Long ecoleId);
	
	List<Classe> findByNomContainingIgnoreCase(String nom);

}
