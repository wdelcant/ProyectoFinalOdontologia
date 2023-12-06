package com.digitalhouse.clinicaOdontologica.services;

import com.digitalhouse.clinicaOdontologica.domain.Odontologo;


import java.util.List;
import java.util.Optional;

public interface OdontologoService {

    Odontologo save(Odontologo odontologo);

    Odontologo update(Odontologo odontologo);

    Optional<Odontologo> findById(Long id);

    Odontologo delete(Long id);

    List<Odontologo> findAll();

    Optional<Odontologo> findByMatricula(Integer matricula);

}


