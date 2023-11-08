package com.digitalhouse.controller;

import com.digitalhouse.domain.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteRestController {

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public Paciente buscarPacientePorId(@PathVariable int id) {

//        if(paciente ==null){
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(paciente);
//        }
        return null;
    }



}