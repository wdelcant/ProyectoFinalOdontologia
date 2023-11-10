package com.digitalhouse.services;

import com.digitalhouse.domain.Paciente;

import java.util.List;

public interface PacienteService {

    Paciente registrar(Paciente paciente) throws Exception;


    Paciente buscar(Long id);

    void eliminar(Long id);

    List<Paciente> buscarTodos() ;

    Paciente actualizar(Paciente paciente) throws Exception;

}
