package com.digitalhouse.clinicaOdontologica.domain.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.ArrayDeque;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String mail;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "usuario")
    private Collection<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> lista = new ArrayDeque<>();

        for (Authority authority : this.authorities) {

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority().name());

            lista.add(grantedAuthority);
        }

        return lista;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Roles getRole() {
        return role;

    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }


}
