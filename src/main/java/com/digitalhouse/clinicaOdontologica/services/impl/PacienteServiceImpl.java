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

import org.springframework.transaction.annotation.Transactional;
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
    public Paciente save(Paciente paciente) throws Exception{
        return pacienteRepository.save(paciente);
    }

    private void validarPaciente(Paciente paciente) throws Exception{
        Objects.requireNonNull(paciente, "El paciente no puede ser nulo");
        Objects.requireNonNull(paciente.getDni(), "El dni no puede ser nulo");
        Objects.requireNonNull(paciente.getNombre(), "El nombre no puede ser nulo");
        Objects.requireNonNull(paciente.getApellido(), "El apellido no puede ser nulo");

        Optional<Paciente> pacienteOptional = findByDni(paciente.getDni());

        if (paciente.getDni() == null && pacienteOptional.isPresent()) {
            throw new Exception("Ya existe un paciente con ese dni");
        }

        if (paciente.getDni() != null && pacienteOptional.isPresent() && paciente.getId().compareTo(pacienteOptional.get().getId()) != 0) {
            throw new Exception("Ya existe un paciente con ese DNI");
        }
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) throws Exception {

        if (paciente.getDni() == null) {
            throw new Exception("El dni no puede ser nulo");
        }
        validarPaciente(paciente);

        // setea fecha de alta automaticamente
        paciente.setFechaAlta(Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC)));

        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente update(Paciente paciente) throws Exception {
        validarPaciente(paciente);
        return save(paciente);
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
    public Optional<Paciente> findByDni(Integer dni) {
        return pacienteRepository.findByDni(dni);
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> cuentas = pacienteRepository.findAll();
        return cuentas;
    }



}
