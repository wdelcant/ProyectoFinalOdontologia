package com.digitalhouse.clinicaOdontologica.services;


import com.digitalhouse.clinicaOdontologica.domain.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    Paciente save(Paciente paciente) throws Exception;

    Paciente update(Paciente paciente) throws Exception;

    void delete(Long id);

    Optional<Paciente> findById(Long id);

    Optional<Paciente> findByDni(Integer dni);

    List<Paciente> findAll();

    Paciente registrarPaciente(Paciente paciente) throws Exception;


}
