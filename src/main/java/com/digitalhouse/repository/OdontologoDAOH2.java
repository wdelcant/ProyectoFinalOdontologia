package com.digitalhouse.repository;

import com.digitalhouse.domain.Odontologo;

import java.sql.Connection;
import java.util.List;


public interface OdontologoDAOH2 {
    Odontologo registrar(Odontologo odontologo) throws Exception;

    Odontologo actualizar(Odontologo odontologo);

    void eliminar(Long id);

    Odontologo buscar(Long id);

    List<Odontologo> buscarTodos();

}
