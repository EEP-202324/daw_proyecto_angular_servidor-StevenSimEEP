package com.example.universidad;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/universidades")
public class UniversidadController {
	private final UniversidadRepository universidadRepository;

	
	public UniversidadController(UniversidadRepository universidadRepository) {
		this.universidadRepository = universidadRepository;
	}
	
	@GetMapping("/{requestedId}")
	private ResponseEntity<Universidad> findById(@PathVariable Long requestedId) {
		Optional<Universidad> universidadOptional = universidadRepository.findById(requestedId);
		if (universidadOptional.isPresent()) {
	        return ResponseEntity.ok(universidadOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping
	private ResponseEntity<Void> crearUniversidad() {
		return null;
	}
	
}
