package com.digitalhouse.rest;

import org.springframework.web.bind.annotation.*;

@RestController
public class PacienteRestController {

    @GetMapping("/pacientes")
    public String getPacientes() {
        return "lista de Pacientes";
    }

    @GetMapping("/pacientes/{id}")
    public String getPacienteById() {
        return "Lista de Pacientes por id";
    }

    @PostMapping("/postpacientes")
    public String postPaciente() {
        return "Alta de Paciente";
    }

    @PutMapping("/putpacientes")
    public String putPaciente() {
        return "Actualizacion de Paciente";
    }

    @DeleteMapping("/delpacientes")
    public String deletePaciente() {
        return "Baja de Paciente";
    }

}
