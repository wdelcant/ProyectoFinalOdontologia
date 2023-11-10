package com.digitalhouse.domain;

import com.digitalhouse.repository.OdontologoDAOH2;
import com.digitalhouse.repository.impl.OdontologoDAOH2Impl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoTest {
    private final static Logger logger = LoggerFactory.getLogger(OdontologoDAOH2Impl.class);

    @Test
    public void probarFuncionalidadDeLog() {

        logger.info("Mensaje de prueba");
    }

    @Test
    public void crearClaseOdontologo() {

        Odontologo odontologo = new Odontologo();

        assertNotNull(odontologo);

        logger.info(odontologo.toString());

    }

    @Test
    public void crearTablaDeServicioYDAO() throws Exception {

        // llamar al DAO para crear la tabla en la Base CREATE TABLE

        OdontologoDAOH2 odontologoTabla = new OdontologoDAOH2Impl();


        logger.info("Tabla ODONTOLOGO creada en la base de datos");

    }

    @Test
    public void creaTablaEnBaseDeDatos() throws Exception {

        Odontologo odontologo = new Odontologo();

        assertNotNull(odontologo);

        logger.info("Odontologo creado: " + odontologo.getNombre() + " " + odontologo.getApellido());

        OdontologoDAOH2 odontologoTabla = new OdontologoDAOH2Impl();

        odontologoTabla.registrar(odontologo);

    }

    @Test
    public void listarOdontologos() {

            OdontologoDAOH2 odontologoTabla = new OdontologoDAOH2Impl();

            List<Odontologo> odontologos = odontologoTabla.buscarTodos();

            for (Odontologo odontologo : odontologos) {

                logger.info(odontologo.toString());
            }

    }

}