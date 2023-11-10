package com.digitalhouse.repository;

import com.digitalhouse.domain.Turno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TurnoDAOH2 {

    Turno registrar(Turno turno);

    void eliminar(Long id);

    Turno buscar(Long id);

    List<Turno> buscarTodos();

    Turno actualizar(Turno turno);

}
