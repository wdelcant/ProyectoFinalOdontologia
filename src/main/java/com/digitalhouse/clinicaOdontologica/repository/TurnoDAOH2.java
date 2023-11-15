package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.domain.Turno;

import java.util.List;

public interface TurnoDAOH2 {

    Turno registrar(Turno turno);

    void eliminar(Long id);

    Turno buscar(Long id);

    List<Turno> buscarTodos();

    Turno actualizar(Turno turno);

}
