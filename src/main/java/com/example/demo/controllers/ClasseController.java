package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import com.example.demo.dao.ClasseRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.entities.Classe;
import com.example.demo.entities.Ecole;

@RestController
@RequestMapping("/classes")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClasseController {

	@Autowired
	ClasseRepository classeRepository;
	
	@Autowired
	EcoleRepository ecoleRepository;
	
	/*
	@GetMapping("/hello")
    public String sayHello() {
		System.out.println("Hello World!");
        return "Hello World!";
    }
	*/
	
	@PostMapping("/addClasseByEcoleId/{id}")
	public ResponseEntity<Classe> addClasseByEcoleId(@PathVariable(value = "id") Long ecoleId,
	        @Valid @RequestBody Classe classe) throws ResourceNotFoundException {
	    Ecole ecole = ecoleRepository.findById(ecoleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Ecole not found for this id : " + ecoleId));
	    classe.setEcole(ecole);
	    System.out.println();
	    Classe savedClasse = classeRepository.save(classe);
	    return ResponseEntity.ok().body(savedClasse);
	}
	
	

	@GetMapping("/getClassesByKeyword")
	public List<Classe> getClassesByKeyword(@RequestParam(value = "keyword", required = false) String keyword) {
	    if (keyword != null && !keyword.isBlank()) {
	        return classeRepository.findByNomContainingIgnoreCase(keyword);
	    } else {
	        return classeRepository.findAll();
	    }
	}
	
	
	//http://localhost:8081/classes/classes?page=1&size=5&sort=id
	@GetMapping("/classes")
	public ResponseEntity<Page<Classe>> getClassesByEcoleIdAndName(
	    @RequestParam(value = "ecole_id", defaultValue = "1") Long ecoleId,
	    @RequestParam(value = "nom", defaultValue = "") String classeName,
	    @RequestParam(value = "page", defaultValue = "0") int page,
	    @RequestParam(value = "size", defaultValue = "10") int size
	) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Classe> classes = null;
	    
	    classes = classeRepository.findByEcoleIdAndNomContainingIgnoreCase(ecoleId, classeName, pageable);
	   
	    return ResponseEntity.ok(classes);
	}
	
	@PutMapping("/editClasse/{id}")
	public ResponseEntity<Classe> editClasse(@PathVariable(value = "id") Long classeId,
	    @Valid @RequestBody Classe updatedClasse) throws ResourceNotFoundException {
	    Classe classe = classeRepository.findById(classeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Classe not found for this id : " + classeId));
	    classe.setNom(updatedClasse.getNom());
	    classe.setNbreleve(updatedClasse.getNbreleve());
	    Classe updated = classeRepository.save(classe);
	    return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/deleteClasse/{id}")
	public Map<String, Boolean> deleteClasse(@PathVariable(value = "id") Long classeId)
	        throws ResourceNotFoundException {
	    Classe classe = classeRepository.findById(classeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Classe not found for this id : " + classeId));
	    classeRepository.delete(classe);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}
}
