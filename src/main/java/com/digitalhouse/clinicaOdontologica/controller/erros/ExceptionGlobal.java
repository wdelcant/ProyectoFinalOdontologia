package com.digitalhouse.clinicaOdontologica.controller.erros;

import com.digitalhouse.clinicaOdontologica.controller.OdontologoRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class ExceptionGlobal {
    private static Logger logger = LoggerFactory.getLogger(OdontologoRestController.class);

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> excepcion(Exception e, WebRequest webRequest){

        logger.error(e.getMessage());

        return ResponseEntity.badRequest().body(e);


    }

    @ExceptionHandler(BadRequestAlertException.class)
    private ResponseEntity<?> excepcion(BadRequestAlertException e, WebRequest webRequest){

        logger.error(e.getMessage());

        return ResponseEntity.badRequest().body(e);


    }
}
