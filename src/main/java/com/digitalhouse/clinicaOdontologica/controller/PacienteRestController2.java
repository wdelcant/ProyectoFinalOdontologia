package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.services.PacienteService;
import com.digitalhouse.clinicaOdontologica.services.impl.PacienteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteRestController2 {


    private PacienteService pacienteService = new PacienteServiceImpl();

    private final static Logger logger = LoggerFactory.getLogger(PacienteRestController2.class);

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        try {
            return ResponseEntity.ok(pacienteService.registrar(paciente));
        }catch (Exception e){

            return  ResponseEntity.badRequest().build();
        }


    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscar(id);

        return ResponseEntity.ok(paciente);
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response = null;

        logger.info("Ingreso a actualizar Paciente");

        if (paciente.getId() != null && pacienteService.buscar(paciente.getId()) != null) {
            try {
                response = ResponseEntity.ok(pacienteService.actualizar(paciente));
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (pacienteService.buscar(id) != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}
