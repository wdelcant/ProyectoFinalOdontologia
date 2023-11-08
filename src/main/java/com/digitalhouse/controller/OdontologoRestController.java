package com.digitalhouse.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/odontologos")
public class OdontologoRestController {
    @GetMapping
    public String getAll() {
        return "Odontologos";
    }
}
