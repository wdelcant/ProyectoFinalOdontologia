package com.digitalhouse.repository;

import com.digitalhouse.domain.Odontologo;

import java.sql.Connection;
import java.util.List;


public interface OdontologoDAOH2 {
    void registrar(Odontologo odontologo) throws Exception;

    void actualizar(Odontologo odontologo);

    void eliminar(int id);

    void buscarPorId(int id);

    List<Odontologo> listarOdontologos() throws Exception;

    void crearTablaOdontologo() throws Exception;

    Connection getConexion();

}
