package com.digitalhouse.clinicaOdontologica.services;

import com.digitalhouse.clinicaOdontologica.domain.Odontologo;

import java.util.List;

public interface OdontologoService {


    Odontologo registrar(Odontologo paciente) throws Exception;

    public Odontologo buscar(Long id);

    public void eliminar(Long id);

    public List<Odontologo> buscarTodos() ;

    Odontologo actualizar(Odontologo paciente);
}


