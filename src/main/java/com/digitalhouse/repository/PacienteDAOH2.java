package com.digitalhouse.repository;

import com.digitalhouse.domain.Paciente;

import java.sql.Connection;
import java.util.List;

public interface PacienteDAOH2 {

    Paciente registrar(Paciente paciente) throws Exception;

    Paciente actualizar(Paciente paciente) throws Exception;

    void eliminar(Long id);

    Paciente buscar(Long id);

    List<Paciente> buscarTodos();



}
