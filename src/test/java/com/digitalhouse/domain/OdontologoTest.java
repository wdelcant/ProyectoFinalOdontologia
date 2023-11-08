package com.digitalhouse.domain;

import com.digitalhouse.repositories.OdontologoDAOH2;
import com.digitalhouse.repositories.OdontologoDAOH2Impl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoTest {
    private final static Logger logger = LoggerFactory.getLogger(OdontologoDAOH2Impl.class);

    @Test
    public void probarFuncionalidadDeLog() {

        logger.info("Mensaje de prueba");
    }

    @Test
    public void crearClaseOdontologo() {

        Odontologo odontologo = new Odontologo(1 , 222222, "Wilson", "Del Canto");

        assertNotNull(odontologo);

        logger.info(odontologo.toString());

    }

    @Test
    public void crearTablaDeServicioYDAO() throws Exception {

        // llamar al DAO para crear la tabla en la Base CREATE TABLE

        OdontologoDAOH2 odontologoTabla = new OdontologoDAOH2Impl();

        odontologoTabla.crearTablaOdontologo();

    }

    @Test
    public void creaTablaEnBaseDeDatos() throws Exception {

        Odontologo odontologo = new Odontologo(2 , 333333, "Marcelo", "Del Canto");

        assertNotNull(odontologo);

        logger.info("Odontologo creado: " + odontologo.toString());

        OdontologoDAOH2 odontologoTabla = new OdontologoDAOH2Impl();

        odontologoTabla.registrar(odontologo);

    }
}