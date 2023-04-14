package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Classe;

public interface ClasseRepository extends JpaRepository<Classe , Long>{
	
	Page<Classe> findByEcoleId(Long ecoleId, Pageable pageable);

	Page<Classe> findByEcoleIdAndNomContainingIgnoreCase(Long ecoleId, String nom, Pageable pageable);
	
	List<Classe> findByNomContainingIgnoreCase(String nom);

}
