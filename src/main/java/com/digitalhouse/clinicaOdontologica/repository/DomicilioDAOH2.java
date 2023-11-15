package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.domain.Domicilio;

import java.util.List;

public interface DomicilioDAOH2 {

    public Domicilio registrar(Domicilio domicilio);

    public void eliminar(Long id);

    public Domicilio buscar(Long id);

    public List<Domicilio> buscarTodos();

    public Domicilio actualizar(Domicilio domicilio) throws Exception ;

}
