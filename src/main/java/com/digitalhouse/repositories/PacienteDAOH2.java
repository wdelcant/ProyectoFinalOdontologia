package com.digitalhouse.repositories;

import com.digitalhouse.domain.Odontologo;
import com.digitalhouse.domain.Paciente;

import java.sql.Connection;
import java.util.List;

public interface PacienteDAOH2 {

    void registrar(Paciente paciente) throws Exception;

    void actualizar(Paciente paciente);

    void eliminar(Long id);

    void buscarPorId(Long id);

    List<Paciente> listarTodos();

    void crearTablaPaciente() throws Exception;

    Connection getConexion();


}
