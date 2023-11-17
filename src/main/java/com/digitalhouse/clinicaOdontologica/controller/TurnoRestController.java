package com.digitalhouse.clinicaOdontologica.controller;


import com.digitalhouse.clinicaOdontologica.domain.Paciente;
import com.digitalhouse.clinicaOdontologica.domain.Turno;
import com.digitalhouse.clinicaOdontologica.services.TurnoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoRestController {

    private static Logger logger = LoggerFactory.getLogger(OdontologoRestController.class);

    private final TurnoService turnoService;
    private TurnoRestController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<Turno> nuevo(@RequestBody Turno turno){

        logger.info("Turno recibido: " + turno.toString());

        try {
            Turno turno1 = turnoService.save(turno);

            return ResponseEntity.ok(turno1);
        }catch (Exception e){
            return ResponseEntity.badRequest().header("error", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<Turno> editar(@RequestBody Turno turno){

        Turno turno1 = turnoService.update(turno);

        return ResponseEntity.ok(turno1);
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){

        List<Turno> turnos = turnoService.findAll();

        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> findById(@PathVariable Long id){

        Optional<Turno> turnoOptional = turnoService.findById(id);

        if(turnoOptional.isPresent()){
            return ResponseEntity.ok(turnoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{pacienteId}/{turnoId}")
public ResponseEntity<Turno> asignarTurno(@PathVariable Long pacienteId, @PathVariable Long turnoId) {
        Turno turno = turnoService.asignarTurno(pacienteId, turnoId);
        return ResponseEntity.ok(turno);
    }
}


