package com.digitalhouse.controller;

import com.digitalhouse.domain.Paciente;
import com.digitalhouse.services.PacienteService;
import com.digitalhouse.services.impl.PacienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes2")
public class PacienteRestController {

    private PacienteService pacienteServi = new PacienteServiceImpl();

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {

        return ResponseEntity.ok(pacienteServi.buscarTodos());


    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente paciente1 = pacienteServi.registrar(paciente);
            return ResponseEntity.ok(paciente1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/eliminar")
    public ResponseEntity<Paciente> buscarPorid(@RequestParam Long id) {

        Paciente paciente = pacienteServi.buscar(id);

        if (paciente == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(paciente);
        }


    }


}