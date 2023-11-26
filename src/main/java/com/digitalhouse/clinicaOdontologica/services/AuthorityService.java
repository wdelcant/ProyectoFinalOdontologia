package com.digitalhouse.clinicaOdontologica.services;

import com.digitalhouse.clinicaOdontologica.domain.security.Authority;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {

    Authority save(Authority cuenta);

    Authority update(Authority cuenta);

    Optional<Authority> findById(Long id);

    void delete(Long id);

    List<Authority> findAll();
}
