package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.controller.OdontologoRestController;
import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import com.digitalhouse.clinicaOdontologica.repository.OdontologoDAOH2;
import com.digitalhouse.clinicaOdontologica.repository.OdontologoRepository;
import com.digitalhouse.clinicaOdontologica.repository.impl.OdontologoDAOH2Impl;
import com.digitalhouse.clinicaOdontologica.services.OdontologoService;

import java.util.List;
import java.util.Optional;

public class OdontologoServiceImpl implements OdontologoService {

    private final OdontologoRepository odontologoRepository;

    public OdontologoServiceImpl(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo save(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo update(Odontologo odontologo) {
        return null;
    }

    @Override
    public Optional<Odontologo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }
}
