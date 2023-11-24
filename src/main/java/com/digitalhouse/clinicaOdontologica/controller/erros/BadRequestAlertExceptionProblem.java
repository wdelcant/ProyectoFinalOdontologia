package com.digitalhouse.clinicaOdontologica.controller.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.AbstractThrowableProblem;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestAlertExceptionProblem extends AbstractThrowableProblem {

}
