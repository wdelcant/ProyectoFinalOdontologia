package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoRestController {

    private static Logger logger = LoggerFactory.getLogger(OdontologoRestController.class);

    @Autowired
    private final OdontologoService odontologoService;

    public OdontologoRestController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> nuevo(@RequestBody Odontologo odontologo) {

        logger.info("Ingreso a nuevo odontologo");

        try {
            Odontologo odontologo1 = odontologoService.save(odontologo);

            logger.info("Se creo el odontologo con exito");

            return ResponseEntity.ok(odontologo1);

        } catch (Exception e) {
            logger.error("Error al crear el odontologo", e.getMessage());

            return ResponseEntity.badRequest().header("error", e.getMessage()).build();

        }
    }

    @PutMapping
    public ResponseEntity<Odontologo> editar(@RequestBody Odontologo odontologo) {

        logger.info("Ingreso a editar odontologo");

        if (odontologo.getId() != null && odontologoService.findById(odontologo.getId()).isPresent()) {
            Odontologo odontologo1 = odontologoService.update(odontologo);
            logger.info("Se edito el odontologo con exito");
            return ResponseEntity.ok(odontologo1);
        } else {
            logger.error("Error al editar el odontologo");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el odontologo");
        }
    }


    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos() {

        logger.info("Ingreso a buscar los odontologos");

        List<Odontologo> odontologos = odontologoService.findAll();

        if (odontologos.isEmpty()) {
            logger.error("No se encontraron odontologos");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("Se encontraron odontologos");
            return ResponseEntity.ok(odontologos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> findById(@PathVariable Long id) {

        Optional<Odontologo> odontologoOptional = odontologoService.findById(id);

        if (odontologoOptional.isPresent()) {
            logger.info("Se encontro el odontologo con id: " + id);
            return ResponseEntity.ok(odontologoOptional.get());
        } else {
            logger.error("No se encontro el odontologo con id: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (odontologoService.findById(id).isPresent()) {
            odontologoService.delete(id);
            logger.info("Se elimino el odontologo con id: " + id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            logger.error("No se encontro el odontologo con id: " + id);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
