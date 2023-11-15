package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.repository.PacienteDAOH2;
import com.digitalhouse.clinicaOdontologica.repository.impl.PacienteDAOH2Impl;
import com.digitalhouse.clinicaOdontologica.services.PacienteService;

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
