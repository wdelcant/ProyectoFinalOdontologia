package com.digitalhouse.services;

import java.time.LocalDateTime;

public class TurnoDTO {

    private String nombrePaciente;

    private String nombreOdontologo;

    private LocalDateTime turno;

    public TurnoDTO(String nombrePaciente, String nombreOdontologo, LocalDateTime turno) {
        this.nombrePaciente = nombrePaciente;
        this.nombreOdontologo = nombreOdontologo;
        this.turno = turno;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreOdontologo() {
        return nombreOdontologo;
    }

    public void setNombreOdontologo(String nombreOdontologo) {
        this.nombreOdontologo = nombreOdontologo;
    }

    public LocalDateTime getTurno() {
        return turno;
    }

    public void setTurno(LocalDateTime turno) {
        this.turno = turno;
    }
}