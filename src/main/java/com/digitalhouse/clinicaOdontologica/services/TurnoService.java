package com.digitalhouse.clinicaOdontologica.services;

import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.domain.Turno;

import java.util.List;
import java.util.Optional;

public interface TurnoService {

        Turno save (Turno turno);
        Turno update (Turno turno);
        void delete (Long id);
        Optional<Turno> findById(Long id);
        List<Turno> findAll();

        Turno asignarTurno(Long pacienteId, Long turnoId);
}

