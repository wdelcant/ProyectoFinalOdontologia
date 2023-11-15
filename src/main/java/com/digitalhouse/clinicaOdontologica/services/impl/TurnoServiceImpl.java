package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.controller.TurnoRestController;
import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.domain.Turno;
import com.digitalhouse.clinicaOdontologica.repository.TurnoDAOH2;
import com.digitalhouse.clinicaOdontologica.repository.TurnoRepository;
import com.digitalhouse.clinicaOdontologica.repository.impl.TurnoDAOH2Impl;
import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import com.digitalhouse.clinicaOdontologica.services.PacienteService;
import com.digitalhouse.clinicaOdontologica.services.TurnoDTO;
import com.digitalhouse.clinicaOdontologica.services.TurnoService;

import java.util.List;
import java.util.Optional;

public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;

    public TurnoServiceImpl(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno save(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Turno update(Turno turno) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Turno> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }
}
