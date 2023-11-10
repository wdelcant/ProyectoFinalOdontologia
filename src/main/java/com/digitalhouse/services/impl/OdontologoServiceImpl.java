package com.digitalhouse.services.impl;

import com.digitalhouse.domain.Odontologo;
import com.digitalhouse.repository.OdontologoDAOH2;
import com.digitalhouse.repository.impl.OdontologoDAOH2Impl;
import com.digitalhouse.services.OdontologoService;

import java.util.List;

public class OdontologoServiceImpl implements OdontologoService {

    private OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2Impl();


    @Override
    public Odontologo registrar(Odontologo paciente) throws Exception {
        try {
            return odontologoDAO.registrar(paciente);
        } catch (Exception e) {
            throw new Exception("No se puede crear un odoncologo con un ID");
        }
    }

    @Override
    public Odontologo buscar(Long id) {
        return odontologoDAO.buscar(id);
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List buscarTodos() {
        return odontologoDAO.buscarTodos();
    }

    @Override
    public Odontologo actualizar(Odontologo o) {
        return null;
    }
}
