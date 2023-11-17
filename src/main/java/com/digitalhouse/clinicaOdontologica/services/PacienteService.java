package com.digitalhouse.clinicaOdontologica.services;


import com.digitalhouse.clinicaOdontologica.domain.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    Paciente save(Paciente paciente);

    Paciente update(Paciente paciente);

    void delete(Long id);

    Optional<Paciente> findById(Long id);

    List<Paciente> findAll();

}
