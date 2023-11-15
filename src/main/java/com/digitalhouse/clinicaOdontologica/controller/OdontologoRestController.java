package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import com.digitalhouse.clinicaOdontologica.services.OdontologoService;
import com.digitalhouse.clinicaOdontologica.services.impl.OdontologoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoRestController {

    private static Logger logger = LoggerFactory.getLogger(OdontologoRestController.class);

    private OdontologoService odontologoService = new OdontologoServiceImpl();

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) throws Exception {
        logger.info("Ingresando a guardar un odontólogo");
        return ResponseEntity.ok(odontologoService.registrar(odontologo));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Long id) {
        Odontologo odontologo = odontologoService.buscar(id);

        return ResponseEntity.ok(odontologo);
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> response = null;

        if (odontologo.getId() != null && odontologoService.buscar(odontologo.getId()) != null)
            response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (odontologoService.buscar(id) != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos() {

        logger.info("Entrando a listar odontólogo");

        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
