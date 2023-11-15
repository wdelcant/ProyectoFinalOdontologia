package com.digitalhouse.clinicaOdontologica.domain;

import com.digitalhouse.clinicaOdontologica.repository.*;
import com.digitalhouse.clinicaOdontologica.repository.impl.PacienteDAOH2Impl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PacienteTest {
    private final static Logger logger = LoggerFactory.getLogger(PacienteDAOH2Impl.class);

    @Test
    public void conexionBaseDeDatos() {

        Conexion conexion = new Conexion();

        assertNotNull(conexion);

        logger.info(conexion.getConexion().toString());
    }

    @Test
    public void crearClasePaciente() {

        Paciente paciente = new Paciente();

        assertNotNull(paciente);

        logger.info(paciente.getNombre());

    }

    @Test
    public void crearTablaDeServicioYDAO() throws Exception {

        // llamar al DAO para crear la tabla en la Base CREATE TABLE

        PacienteDAOH2 pacienteTabla = new PacienteDAOH2Impl();

        logger.info("Tabla Paciente creada en la base de datos");

    }

    @Test
    public void creaTablaEnBaseDeDatos() throws Exception {

        Paciente paciente = new Paciente();

        assertNotNull(paciente);

        logger.info("Paciente creado: " + paciente.getNombre() + " " + paciente.getApellido() );

        PacienteDAOH2 pacienteTabla = new PacienteDAOH2Impl();

        pacienteTabla.registrar(paciente);

    }

    @Test
    public void listarPacientes() {

        PacienteDAOH2 pacienteTabla = new PacienteDAOH2Impl();

        List<Paciente> pacientes = pacienteTabla.buscarTodos();

        for (Paciente paciente : pacientes) {

            logger.info(paciente.toString());
        }
    }

}