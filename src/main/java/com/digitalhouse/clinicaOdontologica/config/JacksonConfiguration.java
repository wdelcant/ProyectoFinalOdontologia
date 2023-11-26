package com.digitalhouse.clinicaOdontologica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;

@Configuration
public class JacksonConfiguration {
    @Bean
    public ProblemModule problemModule() {
        return new ProblemModule();
    }

}
