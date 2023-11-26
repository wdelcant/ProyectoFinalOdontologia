package com.digitalhouse.clinicaOdontologica.controller.erros;

public class BadRequestAlertException extends Exception{
    public BadRequestAlertException(String message) {
        super(message);
    }
}
