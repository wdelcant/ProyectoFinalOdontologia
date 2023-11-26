package com.digitalhouse.clinicaOdontologica.controller.erros;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;


@ControllerAdvice
public class ExceptionGlobalProblem implements ProblemHandling {

    @ExceptionHandler(BadRequestAlertExceptionProblem.class)
    private ResponseEntity<Problem> exception(BadRequestAlertExceptionProblem e) {
        Problem problem = Problem.builder()
                .withType(e.getType())
                .withTitle(e.getTitle())
                .withDetail(e.getMessage()).build();

        return ResponseEntity.badRequest().body(problem);

    }

}
