package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.domain.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);

}
