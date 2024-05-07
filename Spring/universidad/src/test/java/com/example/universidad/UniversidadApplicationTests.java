package com.example.universidad;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UniversidadApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	void devuelveUnaUnoversidadCuandoSeGuardenLosDatos() {
		ResponseEntity<String> response = restTemplate.getForEntity("/universidades/1", String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(1);
		
		String nombre = documentContext.read("$.nombre");
		assertThat(nombre).isEqualTo("Universidad Autónoma de Madrid");
		
		String ubicacion = documentContext.read("$.ubicacion");
		assertThat(ubicacion).isEqualTo("Ciudad Universitaria de Cantoblanco, 28049 Madrid");
		
		String estado = documentContext.read("$.estado");
		assertThat(estado).isEqualTo("Pública");
		
		String photo = documentContext.read("$.photo");
		assertThat(photo).isEqualTo(
				"https://www.comunidad.madrid/sites/default/files/styles/imagen_enlace_opcional/public/aud/educacion/uam_4.jpg?itok=T4uXwmfB");
	
		String disponibilidad = documentContext.read("$.disponibilidad");
		assertThat(disponibilidad).isEqualTo("Abierta");
	}
	
	@Test
	void noDevuelveUniversidadCuandoLaIdEsDesconocida() {
	  ResponseEntity<String> response = restTemplate.getForEntity("/universidades/1000", String.class);

	  assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	  assertThat(response.getBody()).isBlank();
	}
	
	@Test
	void debeCrearNuevaUniversidad() {
	   Universidad nuevaUniversidad = new Universidad(null, "Universidad Carlos III de Madrid", "CALLE MADRID, 126" , "Pública"
			   	, "https://www.comunidad.madrid/sites/default/files/styles/imagen_enlace_opcional/public/aud/educacion/rectorado_uc3m.jpg?itok=CqDwgmkZ"
			   	, "Cerrada");
	   ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidades", nuevaUniversidad, Void.class);
	   assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	
	   URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
	   ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCashCard, String.class);
	   assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
