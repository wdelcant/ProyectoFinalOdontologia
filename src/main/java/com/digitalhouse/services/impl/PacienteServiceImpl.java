package com.digitalhouse.services.impl;

import com.digitalhouse.domain.Paciente;
import com.digitalhouse.repository.PacienteDAOH2;
import com.digitalhouse.repository.impl.PacienteDAOH2Impl;
import com.digitalhouse.services.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class PacienteServiceImpl implements PacienteService {

    private PacienteDAOH2 pacienteDAO = new PacienteDAOH2Impl();
    @Override
    public Paciente registrar(Paciente paciente) throws Exception {
        if(paciente.getId() == null){
            return pacienteDAO.registrar(paciente);
        }else{
            throw new Exception("No se puede crear un paciente con un ID");
        }
    }

    @Override
    public Paciente buscar(Long id) {
        return pacienteDAO.buscar(id);
    }

    @Override
    public void eliminar(Long id) {
        pacienteDAO.eliminar(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        List<Paciente> pacientes = pacienteDAO.buscarTodos();
        return pacientes;
    }

    @Override
    public Paciente actualizar(Paciente paciente) throws Exception {
        return pacienteDAO.actualizar(paciente);
    }
}
