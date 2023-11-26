package com.digitalhouse.clinicaOdontologica.services;

import com.digitalhouse.clinicaOdontologica.domain.security.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Usuario save(Usuario cuenta);

    Usuario update(Usuario cuenta);

    Optional<Usuario> findById(Long id);

    void delete(Long id);

    List<Usuario> findAll();

    void createUser(UserDetails user);
}
