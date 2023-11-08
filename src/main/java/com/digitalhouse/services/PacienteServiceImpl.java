package com.digitalhouse.services;

import com.digitalhouse.domain.Paciente;
import com.digitalhouse.repository.PacienteDAOH2;
import com.digitalhouse.repository.PacienteDAOH2Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class PacienteServiceImpl implements PacienteService{

    private final static Logger logger = LoggerFactory.getLogger(PacienteDAOH2Impl.class);


    @Override
    public void registrarPaciente(Paciente paciente) {

        logger.info("Voy a registrar un paciente");

        PacienteDAOH2 pacienteDAO = new PacienteDAOH2Impl();

        if (paciente.getNombre().isEmpty() || paciente.getApellido().isEmpty() ) {

            logger.error("No se puede registrar el paciente");
        } else {
            try {
                pacienteDAO.registrar(paciente);
                logger.info("Se registro el paciente");
            } catch (SQLException e) {
                logger.error("No se pudo registrar el paciente" + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                logger.error("No se pudo registrar el paciente" + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public void eliminarPaciente(Paciente paciente) {

    }

    @Override
    public List<Paciente> listarPacientes() {
        return null;
    }
}
