package com.digitalhouse.clinicaOdontologica.services.impl;


import com.digitalhouse.clinicaOdontologica.domain.Odontologo;

import com.digitalhouse.clinicaOdontologica.repository.OdontologoRepository;

import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OdontologoServiceImpl implements OdontologoService {

    private final OdontologoRepository odontologoRepository;

    public OdontologoServiceImpl(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo save(Odontologo odontologo) {

        Objects.requireNonNull(odontologo, "El odontologo no puede ser nulo");
        Objects.requireNonNull(odontologo.getNombre(), "El nombre del odontologo no puede ser nulo");
        Objects.requireNonNull(odontologo.getApellido(), "El apellido del odontologo no puede ser nulo");
        Objects.requireNonNull(odontologo.getMatricula(), "La matricula del odontologo no puede ser nula");

        Optional<Odontologo> odontologoOptional = findByMatricula(odontologo.getMatricula());

        if(odontologoOptional.isPresent()){
            throw new RuntimeException("Ya existe un odontólogo con esa matrícula");
        }

        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo update(Odontologo odontologo) {

        return odontologoRepository.save(odontologo);

    }

    @Override
    public Optional<Odontologo> findById(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public Optional<Odontologo> findByMatricula(Integer matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }

    @Override
    public void delete(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> findAll() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        return odontologos;
    }


}
