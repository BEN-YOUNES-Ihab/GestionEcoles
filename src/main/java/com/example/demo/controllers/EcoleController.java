package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EcoleRepository;
import com.example.demo.entities.Ecole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

@RestController
@RequestMapping("/ecoles")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EcoleController {
	
	@Autowired
	private EcoleRepository ecoleRepository;
	
	@GetMapping("")
	public List<Ecole> getAllEcoles() {
		return ecoleRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ecole> getEcoleById(@PathVariable(value = "id") Long ecoleId)
			throws ResourceNotFoundException {
		Ecole ecole = ecoleRepository.findById(ecoleId)
				.orElseThrow(() -> new ResourceNotFoundException("Ecole not found for this id :: " + ecoleId));
		return ResponseEntity.ok().body(ecole);
	}

	@PostMapping("")
	public Ecole createEcole(@Valid @RequestBody Ecole ecole) {
		return ecoleRepository.save(ecole);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ecole> updateEcole(@PathVariable(value = "id") Long ecoleId,
			@Valid @RequestBody Ecole ecoleDetails) throws ResourceNotFoundException {
		Ecole ecole = ecoleRepository.findById(ecoleId)
				.orElseThrow(() -> new ResourceNotFoundException("Ecole not found for this id :: " + ecoleId));

		ecole.setNom(ecoleDetails.getNom());
		ecole.setAdresse(ecoleDetails.getAdresse());
		ecole.setClasses(ecoleDetails.getClasses());
		
		final Ecole updatedEcole = ecoleRepository.save(ecole);
		return ResponseEntity.ok(updatedEcole);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEcole(@PathVariable(value = "id") Long ecoleId)
			throws ResourceNotFoundException {
		Ecole ecole = ecoleRepository.findById(ecoleId)
				.orElseThrow(() -> new ResourceNotFoundException("Ecole not found for this id :: " + ecoleId));

		ecoleRepository.delete(ecole);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}