package com.example.universidad;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	private ResponseEntity<Void> crearUniversidad(@RequestBody Universidad newUniversidadRequest
				, UriComponentsBuilder ucb) {
		Universidad savedCashCard = universidadRepository.save(newUniversidadRequest);
		   URI locationOfNewCashCard = ucb
		            .path("cashcards/{id}")
		            .buildAndExpand(savedCashCard.getId())
		            .toUri();
		   return ResponseEntity.created(locationOfNewCashCard).build();
	}
	
}
