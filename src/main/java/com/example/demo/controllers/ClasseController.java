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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ClasseController {

	@Autowired
	ClasseRepository classeRepository;
	
	@Autowired
	EcoleRepository ecoleRepository;
	
	@GetMapping("/hello")
    public String sayHello() {
		System.out.println("Hello World!");
        return "Hello World!";
    }
	
	@PostMapping("/addClasseByEcoleId/{id}")
	public ResponseEntity<Classe> addClasseByEcoleId(@PathVariable(value = "id") Long ecoleId,
	        @Valid @RequestBody Classe classe) throws ResourceNotFoundException {
	    Ecole ecole = ecoleRepository.findById(ecoleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Ecole not found for this id : " + ecoleId));
	    classe.setEcole(ecole);
	    Classe savedClasse = classeRepository.save(classe);
	    return ResponseEntity.ok().body(savedClasse);
	}
	
	@GetMapping("/getClassesByEcoleId/{id}")
	public List<Classe> getClassesByEcoleId(@PathVariable(value = "id") Long ecoleId) {
	    return classeRepository.findByEcoleId(ecoleId);
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
	public ResponseEntity<Map<String, Object>> getAllClassesPage(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size,
	    @RequestParam(defaultValue = "id,asc") String[] sort) {

	    try {
	        List<Classe> classes = new ArrayList<Classe>();
	        Pageable paging = PageRequest.of(page, size, Sort.by(sort));

	        Page<Classe> pageClasses;
	        pageClasses = classeRepository.findAll(paging);

	        classes = pageClasses.getContent();

	        Map<String, Object> response = new HashMap<>();
	        response.put("classes", classes);
	        response.put("currentPage", pageClasses.getNumber());
	        response.put("totalClasses", pageClasses.getTotalElements());
	        response.put("totalPages", pageClasses.getTotalPages());

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
