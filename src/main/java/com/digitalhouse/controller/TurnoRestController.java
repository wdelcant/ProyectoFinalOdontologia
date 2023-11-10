package com.digitalhouse.controller;

import com.digitalhouse.domain.Turno;
import com.digitalhouse.services.TurnoDTO;
import com.digitalhouse.services.TurnoService;
import com.digitalhouse.services.impl.TurnoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoRestController {

    private final static Logger logger = LoggerFactory.getLogger(TurnoRestController.class);

    private final TurnoService turnoService = new TurnoServiceImpl();

    @PostMapping
    public ResponseEntity<TurnoDTO> crearTurno(@RequestBody Turno turno) {

        logger.info("Ingresando a crear un turno " + turno.toString());

        try {
            TurnoDTO turno1 = turnoService.crearTurno(turno);

            return ResponseEntity.ok(turno1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {

        return ResponseEntity.ok().body(turnoService.buscarTodos());
    }
}
