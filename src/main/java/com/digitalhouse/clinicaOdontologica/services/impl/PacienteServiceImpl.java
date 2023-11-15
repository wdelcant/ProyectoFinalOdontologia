package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.repository.PacienteDAOH2;
import com.digitalhouse.clinicaOdontologica.repository.PacienteRepository;
import com.digitalhouse.clinicaOdontologica.repository.impl.PacienteDAOH2Impl;
import com.digitalhouse.clinicaOdontologica.services.PacienteService;

import java.util.List;
import java.util.Optional;

public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente update(Paciente paciente) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}
