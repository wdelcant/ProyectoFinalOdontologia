package com.digitalhouse.services;

import com.digitalhouse.domain.Odontologo;

import java.util.List;

public interface OdontologoService {


    public void registrarOdontologo(Odontologo odontologo);

    public void eliminarOdontologo(Odontologo odontologo);

    public List<Odontologo> listarOdontologos();




}
