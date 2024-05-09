package com.example.universidad;

import java.net.URI;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Universidad savedUniversidad = universidadRepository.save(newUniversidadRequest);
		   URI locationOfNewUniversidad = ucb
		            .path("universidades/{id}")
		            .buildAndExpand(savedUniversidad.getId())
		            .toUri();
		   return ResponseEntity.created(locationOfNewUniversidad).build();
	}
	
	@GetMapping()
	private ResponseEntity<Iterable<Universidad>> findAll() {
		return ResponseEntity.ok(universidadRepository.findAll());
	}
	
}
