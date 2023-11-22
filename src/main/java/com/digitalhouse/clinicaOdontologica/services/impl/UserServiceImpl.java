package com.digitalhouse.clinicaOdontologica.services.impl;

import com.digitalhouse.clinicaOdontologica.domain.security.Authority;
import com.digitalhouse.clinicaOdontologica.domain.security.Roles;
import com.digitalhouse.clinicaOdontologica.domain.security.Usuario;
import com.digitalhouse.clinicaOdontologica.repository.UserRepository;
import com.digitalhouse.clinicaOdontologica.services.AuthorityService;
import com.digitalhouse.clinicaOdontologica.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final AuthorityService authorityService;

    public UserServiceImpl(UserRepository userRepository, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario cuenta) {
        return null;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Override
    public void createUser(UserDetails user) {

        Optional<Usuario> usuarioOptional = userRepository.findByUsername(user.getUsername());
        if (!usuarioOptional.isPresent()) {

            Usuario usuario1 = new Usuario();
            usuario1.setUsername(user.getUsername());
            usuario1.setPassword(user.getPassword());


            save(usuario1);

            for (GrantedAuthority grantedAuthority : user.getAuthorities()) {

                Authority authority = new Authority();
                authority.setAuthority(Roles.valueOf(grantedAuthority.getAuthority()));
                authority.setUsuario(usuario1);

                authorityService.save(authority);

            }
        }

    }
}