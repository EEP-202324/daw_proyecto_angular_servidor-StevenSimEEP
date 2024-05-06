package com.example.universidad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {

	@GetMapping("/{requestedId}")
	private ResponseEntity<Universidad> findById(@PathVariable Long requestedId) {
		if (requestedId.equals(99L)) {
	        Universidad universidad = new Universidad(0L, "Universidad Autónoma de Madrid"
	        			, "Ciudad Universitaria de Cantoblanco, 28049 Madrid", "Pública"
	        			, "https://www.comunidad.madrid/sites/default/files/styles/imagen_enlace_opcional/public/aud/educacion/uam_4.jpg?itok=T4uXwmfB"
	        			, "Abierta");
	        return ResponseEntity.ok(universidad);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
