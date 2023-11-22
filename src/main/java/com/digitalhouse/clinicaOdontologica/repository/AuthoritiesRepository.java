package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {


}
