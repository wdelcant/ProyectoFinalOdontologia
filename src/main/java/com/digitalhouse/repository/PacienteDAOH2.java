package com.digitalhouse.repository;

import com.digitalhouse.domain.Paciente;

import java.sql.Connection;
import java.util.List;

public interface PacienteDAOH2 {

    void registrar(Paciente paciente) throws Exception;

    void actualizar(Paciente paciente);

    void eliminar(int id);

    void buscarPorId(int id);

    List<Paciente> listarPacientes();

    void crearTablaPaciente() throws Exception;

    Connection getConexion();


}
