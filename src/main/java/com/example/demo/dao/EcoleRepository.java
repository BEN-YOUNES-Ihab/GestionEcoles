package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Ecole;


public interface EcoleRepository extends JpaRepository<Ecole, Long>{

	List<Ecole> findByNomContainingIgnoreCase(String keyword);

}
