package com.example.universidad;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class UniversidadJsonTest {

	@Autowired
	private JacksonTester<Universidad> json;

	@Test
	void universidadSerializationTest() throws IOException {
		Universidad universidad = new Universidad(0L, "Universidad Autónoma de Madrid",
				"Ciudad Universitaria de Cantoblanco, 28049 Madrid", "Pública",
				"https://www.comunidad.madrid/sites/default/files/styles/imagen_enlace_opcional/public/aud/educacion/uam_4.jpg?itok=T4uXwmfB",
				"Abierta");
		assertThat(json.write(universidad)).isStrictlyEqualToJson("expected.json");
		assertThat(json.write(universidad)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(universidad)).extractingJsonPathNumberValue("@.id").isEqualTo(0);
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.nombre");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.nombre")
				.isEqualTo("Universidad Autónoma de Madrid");
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.ubicacion");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.ubicacion")
				.isEqualTo("Ciudad Universitaria de Cantoblanco, 28049 Madrid");
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.estado");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.estado").isEqualTo("Pública");
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.photo");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.photo").isEqualTo(
				"https://www.comunidad.madrid/sites/default/files/styles/imagen_enlace_opcional/public/aud/educacion/uam_4.jpg?itok=T4uXwmfB");
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.disponibilidad");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.disponibilidad").isEqualTo("Abierta");

	}

	@Test
	void universidadDeserializationTest() throws IOException {
		String expected = """
				{
				    "id": 0,
					"nombre": "Universidad Autónoma de Madrid",
					"ubicacion": "Ciudad Universitaria de Cantoblanco, 28049 Madrid",
					"estado": "Pública",
					"photo": "https://www.comunidad.madrid/sites/default/files/styles/imagen_enlace_opcional/public/aud/educacion/uam_4.jpg?itok=T4uXwmfB",
					"disponibilidad": "Abierta"
				}
				""";
//       assertThat(json.parse(expected)).isEqualTo(new Universidad(99L, 123.45));
		assertThat(json.parseObject(expected).getId()).isEqualTo(0);
		assertThat(json.parseObject(expected).getNombre()).isEqualTo("Universidad Autónoma de Madrid");
		assertThat(json.parseObject(expected).getUbicacion()).isEqualTo(
				"Ciudad Universitaria de Cantoblanco, 28049 Madrid");
		assertThat(json.parseObject(expected).getEstado()).isEqualTo("Pública");
		assertThat(json.parseObject(expected).getPhoto()).isEqualTo("https://www.comunidad.madrid/sites/default/files/styles/imagen_enlace_opcional/public/aud/educacion/uam_4.jpg?itok=T4uXwmfB");
		assertThat(json.parseObject(expected).getDisponibilidad()).isEqualTo("Abierta");
	}
}
