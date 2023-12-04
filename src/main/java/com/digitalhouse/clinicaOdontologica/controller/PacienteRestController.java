package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.domain.Paciente;

import com.digitalhouse.clinicaOdontologica.services.PacienteService;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) throws Exception {
        ResponseEntity<Paciente> reponse = null;

        if (paciente.getId() != null && pacienteService.findById(paciente.getId()).isPresent()) {
            try {
                reponse = ResponseEntity.ok(pacienteService.update(paciente));
                logger.info("Se actualizo el paciente con id: " + paciente.getId());
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "El paciente no pudo ser actualizado", e);
            }
        } else {
            throw new Exception("El paciente no existe");
        }
        return reponse;
    }


    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {

        List<Paciente> pacientes = pacienteService.findAll();

        logger.info("Se devolvieron la cantidad de " + pacientes.size() + " pacientes" );

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
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (pacienteService.findById(id).isPresent()) {
            pacienteService.delete(id);
            logger.info("Se elimino el paciente con id: " + id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se elimino el paciente con id: " + id);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            logger.error("No se encontro el paciente con id: " + id);
        }
        return response;
    }


}