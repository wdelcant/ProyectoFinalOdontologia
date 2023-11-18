package com.digitalhouse.clinicaOdontologica.domain;


import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class OdontologoTest {
    private final static Logger logger = LoggerFactory.getLogger(Odontologo.class);

    @Autowired
    private OdontologoService odontologoService;
    @Test
    void shouldThrowExceptionWhenOdontologoDoesNotExist() {
        // Given
        Odontologo odontologo = new Odontologo();
        odontologo.setId(99L); // non-existing ID
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(12345);

        // When
        Exception exception = assertThrows(ResponseStatusException.class, () -> odontologoService.update(odontologo));

        // Then
        assertEquals("404 NOT_FOUND \"Odontologo with ID 999 not found\"", exception.getMessage());
    }

    @Test
    void shouldUpdateOdontologoInDatabase() {
        // Given
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(12345);
        Odontologo savedOdontologo = odontologoService.save(odontologo);

        // When
        savedOdontologo.setNombre("Pedro");
        Odontologo updatedOdontologo = odontologoService.update(savedOdontologo);

        // Then
        assertNotNull(updatedOdontologo);
        assertEquals("Pedro", updatedOdontologo.getNombre());
        assertEquals(odontologo.getApellido(), updatedOdontologo.getApellido());
        assertEquals(odontologo.getMatricula(), updatedOdontologo.getMatricula());
    }
}