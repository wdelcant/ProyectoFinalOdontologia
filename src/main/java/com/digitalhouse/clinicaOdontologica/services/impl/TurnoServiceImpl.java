package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.domain.Turno;
import com.digitalhouse.clinicaOdontologica.repository.TurnoDAOH2;
import com.digitalhouse.clinicaOdontologica.repository.impl.TurnoDAOH2Impl;
import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import com.digitalhouse.clinicaOdontologica.services.PacienteService;
import com.digitalhouse.clinicaOdontologica.services.TurnoDTO;
import com.digitalhouse.clinicaOdontologica.services.TurnoService;

import java.util.List;

public class TurnoServiceImpl implements TurnoService {

    private TurnoDAOH2 turnoDAO = new TurnoDAOH2Impl() {
    };

    private OdontologoService odontologoService = new OdontologoServiceImpl();

    private PacienteService pacienteService = new PacienteServiceImpl();

    @Override
    public TurnoDTO crearTurno(Turno turno) throws Exception {
        //Validamos que exista el paciente

        Paciente paciente = pacienteService.buscar(turno.getPaciente().getId());

        if(paciente == null){
            throw new Exception("El paciente es Nulo");
        }

        Odontologo odontologo = odontologoService.buscar(turno.getOdontologo().getId());

        if(odontologo == null){
            throw new Exception("El Odontólogo es Nulo");
        }

        if(turno.getTurno() == null){ //Checkear que el turno no esté ocupado
            throw new Exception("No se ha especificado la fecha/hora del turno");
        }

        Turno turno1 = turnoDAO.registrar(turno);

        if(turno1 != null){
            return new TurnoDTO(paciente.getNombre(), odontologo.getNombre(), turno1.getTurno());
        }else{
            throw new Exception("No se ha creado el turno");
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnoDAO.buscarTodos();
    }
}
