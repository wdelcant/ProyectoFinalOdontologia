package com.digitalhouse.clinicaOdontologica.services.impl;


import com.digitalhouse.clinicaOdontologica.domain.Odontologo;

import com.digitalhouse.clinicaOdontologica.repository.OdontologoRepository;

import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
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
    public void delete(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> findAll() {
        List<Odontologo> odontologos =  odontologoRepository.findAll();
        return odontologos;
    }
}
