package com.digitalhouse.clinicaOdontologica.domain;

public class Usuario {
    private Long id;
    private String usuario;

    private String password;

    private String rol;

    public Usuario() {
    }

    public Usuario(String usuario, String password, String rol) {
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(Long id, String usuario, String password, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
