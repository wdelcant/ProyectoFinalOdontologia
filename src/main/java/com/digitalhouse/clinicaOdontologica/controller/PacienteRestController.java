package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.domain.Paciente;

import com.digitalhouse.clinicaOdontologica.services.PacienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteRestController {


    private static Logger logger = LoggerFactory.getLogger(OdontologoRestController.class);

    private final PacienteService pacienteService;

    private PacienteRestController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @PostMapping
    public ResponseEntity<Paciente> nuevo(@RequestBody Paciente paciente) {

        logger.info("Se creo el paciente con nombre: " + paciente.getNombre());

        try {
            Paciente paciente1 = pacienteService.save(paciente);
            logger.info("Se creo el paciente con id: " + paciente1.getId());

            return ResponseEntity.ok(paciente1);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().header("error", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> editar(@RequestBody Paciente paciente) {

        Paciente paciente1 = pacienteService.update(paciente);
        logger.info("Se edito el paciente con id: " + paciente1.getId());

        return ResponseEntity.ok(paciente1);


    }


    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {

        List<Paciente> pacientes = pacienteService.findAll();

        logger.info("Se devolvieron todos los pacientes");

        return ResponseEntity.ok(pacientes);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {

        Optional<Paciente> pacienteOptional = pacienteService.findById(id);

        logger.info("Se busco el paciente con id: " + id);

        if (pacienteOptional.isPresent()) {
            logger.info("Se devolvio el paciente con id: " + id);
            return ResponseEntity.ok(pacienteOptional.get());
        } else {
            logger.error("No se encontro el paciente con id: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pacienteService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }


}