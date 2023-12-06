package com.digitalhouse.clinicaOdontologica.domain;


import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;



@SpringBootTest
public class OdontologoTest {
    private final static Logger logger = LoggerFactory.getLogger(Odontologo.class);

    @Autowired
    private OdontologoService odontologoService;

    @Test
    void shouldThrowExceptionWhenOdontologoDoesNotExist() {
        Assertions.assertThrows(ResponseStatusException.class, () -> odontologoService.findById(1L));
    }

    @Test
    void shouldCreateOdontologoInDatabase() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Dr. Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(123456);

        Odontologo savedOdontologo = odontologoService.save(odontologo);
        logger.info("Odontologo saved: " + savedOdontologo.toString());

        Assertions.assertNotNull(savedOdontologo.getId());
        Assertions.assertEquals(odontologo.getNombre(), savedOdontologo.getNombre());
        Assertions.assertEquals(odontologo.getApellido(), savedOdontologo.getApellido());
        Assertions.assertEquals(odontologo.getMatricula(), savedOdontologo.getMatricula());


    }

    @Test
    void shouldUpdateOdontologoInDatabase() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Dr. Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(123456);

        Odontologo savedOdontologo = odontologoService.update(odontologo);
        savedOdontologo.setNombre("Dr. Pedro");
        Odontologo updatedOdontologo = odontologoService.update(savedOdontologo);
        logger.info("Odontologo saved: " + savedOdontologo.toString());

        Assertions.assertEquals("Dr. Pedro", updatedOdontologo.getNombre());
    }

    @Test
    void shouldDeleteOdontologoFromDatabase() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Dr. Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula(123456);

        Odontologo savedOdontologo = odontologoService.delete(odontologo.getId());
        odontologoService.delete(savedOdontologo.getId());

        Assertions.assertThrows(ResponseStatusException.class, () -> odontologoService.findById(savedOdontologo.getId()));

        logger.info("Odontologo deleted: " + savedOdontologo.toString());
    }
}