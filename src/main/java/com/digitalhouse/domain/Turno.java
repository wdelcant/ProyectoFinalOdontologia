package com.digitalhouse.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Turno {

    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;

    private LocalDateTime turno;

    public Turno() {
    }

    public Turno(Long idTurno, Paciente paciente, Odontologo odontologo, LocalDateTime turno) {

        this.id = idTurno;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.turno = turno;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getTurno() {
        return turno;
    }

    public void setTurno(LocalDateTime turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", turno=" + turno +
                '}';
    }
}