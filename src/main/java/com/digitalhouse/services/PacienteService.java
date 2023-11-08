package com.digitalhouse.services;

import com.digitalhouse.domain.Paciente;

import java.util.List;

public interface PacienteService {

    public void registrarPaciente(Paciente paciente);

    public void eliminarPaciente(Paciente paciente);

    public List<Paciente> listarPacientes();


}
