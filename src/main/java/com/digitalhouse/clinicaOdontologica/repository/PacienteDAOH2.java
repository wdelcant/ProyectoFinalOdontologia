package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.domain.Paciente;

import java.util.List;

public interface PacienteDAOH2 {

    Paciente registrar(Paciente paciente) throws Exception;

    Paciente actualizar(Paciente paciente) throws Exception;

    void eliminar(Long id);

    Paciente buscar(Long id);

    List<Paciente> buscarTodos();



}
