
package com.digitalhouse.clinicaOdontologica.domain;


import com.digitalhouse.clinicaOdontologica.services.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

public class PacienteTest {
    private final static Logger logger = LoggerFactory.getLogger(Paciente.class);

    @Autowired
    private PacienteService pacienteService;

    @Test
    void shouldCreatePacienteInDatabase() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setNombre("Matias");
        paciente.setApellido("Del Canto");
        paciente.setDni(12345678);
        paciente.setFechaAlta(new Date());

        Paciente savedPaciente = pacienteService.save(paciente);
        logger.info("Paciente saved: " + savedPaciente.toString());

        Assertions.assertNotNull(savedPaciente.getId());
        Assertions.assertEquals(paciente.getNombre(), savedPaciente.getNombre());
        Assertions.assertEquals(paciente.getApellido(), savedPaciente.getApellido());
        Assertions.assertEquals(paciente.getDni(), savedPaciente.getDni());
    }

    @Test
    void shouldThrowExceptionWhenPacienteDoesNotExist() {
        Assertions.assertThrows(ResponseStatusException.class, () -> pacienteService.findById(1L));
    }

    @Test
    void shouldUpdatePacienteInDatabase() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setNombre("Matias");
        paciente.setApellido("Del Canto");
        paciente.setDni(12345678);
        paciente.setFechaAlta(new Date());

        Paciente savedPaciente = pacienteService.save(paciente);
        savedPaciente.setNombre("Wilson");
        Paciente updatedPaciente = pacienteService.update(savedPaciente);
        logger.info("Paciente update: " + savedPaciente.toString());

        Assertions.assertEquals("Wilson", updatedPaciente.getNombre());
    }

    @Test
    void shouldDeletePacienteFromDatabase() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setNombre("Matias");
        paciente.setApellido("Del Canto");
        paciente.setDni(12345678);
        paciente.setFechaAlta(new Date());

        Paciente savedPaciente = pacienteService.save(paciente);
        pacienteService.delete(savedPaciente.getId());
        logger.info("Paciente deleted: " + savedPaciente.toString());

        Assertions.assertThrows(ResponseStatusException.class, () -> pacienteService.findById(savedPaciente.getId()));
    }
}