package com.digitalhouse.services;

import com.digitalhouse.domain.Turno;

import java.util.List;

public interface TurnoService {
    TurnoDTO crearTurno(Turno turno) throws Exception;

    List<Turno> buscarTodos();
}

