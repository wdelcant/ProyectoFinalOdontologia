package com.digitalhouse.repositories;

import com.digitalhouse.domain.Odontologo;

import java.sql.Connection;
import java.util.List;


public interface OdontologoDAOH2 {
    void registrar(Odontologo odontologo) throws Exception;

    void actualizar(Odontologo odontologo);

    void eliminar(Long id);

    void buscarPorId(Long id);

    List<Odontologo> listarTodos();

    void crearTablaOdontologo() throws Exception;

    Connection getConexion();

}
