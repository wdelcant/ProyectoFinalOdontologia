package com.digitalhouse.services;

import com.digitalhouse.domain.Odontologo;
import com.digitalhouse.repository.OdontologoDAOH2;
import com.digitalhouse.repository.OdontologoDAOH2Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class OdontologoServiceImpl implements OdontologoService {

    private final static Logger logger = LoggerFactory.getLogger(OdontologoDAOH2Impl.class);


    @Override
    public void registrarOdontologo(Odontologo odontologo) {

        logger.info("Voy a registrar un odontologo");

        OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2Impl();

        if (odontologo.getNombre().isEmpty() || odontologo.getApellido().isEmpty() || odontologo.getNumeroMatricula() < 0) {

            logger.error("No se puede registrar el odontologo");
        } else {
            try {
                odontologoDAO.registrar(odontologo);
                logger.info("Se registro el odotologo");
            } catch (SQLException e) {
                logger.error("No se pudo registrar el odontologo" + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                logger.error("No se pudo registrar el odontologo" + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public void eliminarOdontologo(Odontologo odontologo) {

        logger.info("Voy a eliminar un odontologo");

    }

    @Override
    public List<Odontologo> listarOdontologos() {
        
        return null;
    }
}
