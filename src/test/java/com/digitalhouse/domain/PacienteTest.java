package com.digitalhouse.domain;

import com.digitalhouse.repository.Conexion;
import com.digitalhouse.repository.PacienteDAOH2;
import com.digitalhouse.repository.PacienteDAOH2Impl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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

        Paciente paciente = new Paciente(2, "Wilson", "Del Canto", "Av. Siempre Viva 123", 222222, null);

        assertNotNull(paciente);

        logger.info(paciente.getNombre());

    }

    @Test
    public void crearTablaDeServicioYDAO() throws Exception {

        // llamar al DAO para crear la tabla en la Base CREATE TABLE

        PacienteDAOH2 pacienteTabla = new PacienteDAOH2Impl();

        pacienteTabla.crearTablaPaciente();

        logger.info("Tabla Paciente creada en la base de datos");

    }

    @Test
    public void creaTablaEnBaseDeDatos() throws Exception {

        Paciente paciente = new Paciente(3, "Wilson", "Del Canto", "Av. Siempre Viva 123", 174533520, new Date());

        assertNotNull(paciente);

        logger.info("Paciente creado: " + paciente.getNombre() + " " + paciente.getApellido() );

        PacienteDAOH2 pacienteTabla = new PacienteDAOH2Impl();

        pacienteTabla.registrar(paciente);

    }

}