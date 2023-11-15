package com.digitalhouse.clinicaOdontologica.services;

import com.digitalhouse.clinicaOdontologica.domain.Turno;

import java.util.List;

public interface TurnoService {
    TurnoDTO crearTurno(Turno turno) throws Exception;

    List<Turno> buscarTodos();
}

