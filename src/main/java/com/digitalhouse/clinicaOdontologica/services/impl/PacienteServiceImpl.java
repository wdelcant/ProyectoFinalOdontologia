package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.domain.Turno;
import com.digitalhouse.clinicaOdontologica.repository.PacienteRepository;
import com.digitalhouse.clinicaOdontologica.services.PacienteService;
import com.digitalhouse.clinicaOdontologica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente save(Paciente paciente) {
        Objects.requireNonNull(paciente, "El paciente no puede ser nulo");
        Objects.requireNonNull(paciente.getDni(), "El dni no puede ser nulo");
        // agregar fecha de alta
        paciente.setFechaAlta(Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC)));


        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente update(Paciente paciente) {
        return null;
    }

    @Override
    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> cuentas = pacienteRepository.findAll();
        return cuentas;
    }


}
