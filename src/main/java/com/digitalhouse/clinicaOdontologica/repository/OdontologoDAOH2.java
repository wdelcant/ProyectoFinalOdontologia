package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.domain.Odontologo;

import java.util.List;


public interface OdontologoDAOH2 {
    Odontologo registrar(Odontologo odontologo) throws Exception;

    Odontologo actualizar(Odontologo odontologo);

    void eliminar(Long id);

    Odontologo buscar(Long id);

    List<Odontologo> buscarTodos();

}
