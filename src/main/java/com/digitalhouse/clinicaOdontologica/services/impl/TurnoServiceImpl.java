package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.domain.Turno;
import com.digitalhouse.clinicaOdontologica.repository.TurnoRepository;
import com.digitalhouse.clinicaOdontologica.services.PacienteService;
import com.digitalhouse.clinicaOdontologica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@Transactional
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private PacienteService pacienteService;

    private final TurnoRepository turnoRepository;

    public TurnoServiceImpl(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno save(Turno turno) {
        Objects.requireNonNull(turno, "El turno no puede ser nulo");
        Objects.requireNonNull(turno.getOdontologo(), "El odontologo no puede ser nulo");
        Objects.requireNonNull(turno.getFechaHora(), "La fecha y hora no pueden ser nulas");

        return turnoRepository.save(turno);
    }

    @Override
    public Turno update(Turno turno) {
        return null;
    }

    @Override
    public void delete(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Optional<Turno> findById(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> findAll() {
        List<Turno> turnos =  turnoRepository.findAll();
        return turnos;
    }
    @Override
    public Turno asignarTurno(Long pacienteId, Long turnoId) {

        Paciente paciente = pacienteService.findById(pacienteId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Paciente with ID " + pacienteId + " not found"));

        Turno turno = findById(turnoId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Turno with ID " + turnoId + " not found"));

        turno.setPaciente(paciente);
        return save(turno);
    }
}
