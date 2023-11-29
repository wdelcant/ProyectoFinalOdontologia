package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.domain.security.Authority;
import com.digitalhouse.clinicaOdontologica.repository.AuthoritiesRepository;
import com.digitalhouse.clinicaOdontologica.services.AuthorityService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthoritiesRepository authoritiesRespository;

    public AuthorityServiceImpl(AuthoritiesRepository authoritiesRespository) {
        this.authoritiesRespository = authoritiesRespository;
    }

    @Override
    public Authority save(Authority authority) {
        return authoritiesRespository.save(authority);
    }

    @Override
    public Authority update(Authority cuenta) {
        return null;
    }

    @Override
    public Optional<Authority> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Authority> findAll() {
        return null;
    }

}
