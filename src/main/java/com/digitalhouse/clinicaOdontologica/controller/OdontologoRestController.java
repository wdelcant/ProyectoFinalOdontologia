package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoRestController {

    private static Logger logger = LoggerFactory.getLogger(OdontologoRestController.class);

    private final OdontologoService odontologoService;

    public OdontologoRestController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> nuevo(@RequestBody Odontologo odontologo){

        logger.info("Ingreso a nuevo odontologo");

        try {
            Odontologo odontologo1 = odontologoService.save(odontologo);

            return ResponseEntity.ok(odontologo1);

        }catch (Exception e){
            return ResponseEntity.badRequest().header("error", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<Odontologo> editar(@RequestBody Odontologo odontologo){

        Odontologo odontologo1 = odontologoService.update(odontologo);

        return ResponseEntity.ok(odontologo1);
    }


    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){

        List<Odontologo> odontologos = odontologoService.findAll();

        return ResponseEntity.ok(odontologos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> findById(@PathVariable Long id){

        Optional<Odontologo> odontologoOptional = odontologoService.findById(id);

        if(odontologoOptional.isPresent()){
            return ResponseEntity.ok(odontologoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }


    }

}
