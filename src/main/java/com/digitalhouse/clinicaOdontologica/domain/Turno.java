package com.digitalhouse.clinicaOdontologica.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Odontologo odontologo;
    @Column(name = "turno")
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