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
        Universidad universidad = new Universidad(99L, 123.45);
        assertThat(json.write(universidad)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(universidad)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(universidad)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(universidad)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(universidad)).extractingJsonPathNumberValue("@.amount")
             .isEqualTo(123.45);
    }
}
