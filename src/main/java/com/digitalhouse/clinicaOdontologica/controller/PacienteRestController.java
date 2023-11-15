package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.domain.Paciente;

import com.digitalhouse.clinicaOdontologica.services.PacienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public ResponseEntity<Paciente> nuevo(@RequestBody Paciente paciente){

        logger.info("Paciente recibido: " + paciente.toString());

        try {
            Paciente paciente1 = pacienteService.save(paciente);

            return ResponseEntity.ok(paciente1);
        }catch (Exception e){
            return ResponseEntity.badRequest().header("error", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> editar(@RequestBody Paciente paciente){

        Paciente paciente1 = pacienteService.update(paciente);

        return ResponseEntity.ok(paciente1);


    }


    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){

        List<Paciente> pacientes = pacienteService.findAll();

        return ResponseEntity.ok(pacientes);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id){

        Optional<Paciente> pacienteOptional = pacienteService.findById(id);

        if(pacienteOptional.isPresent()){
            return ResponseEntity.ok(pacienteOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }


    }
}