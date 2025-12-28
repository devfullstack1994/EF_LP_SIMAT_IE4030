package com.mycompany.ef_lp_simat_ie4030.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int idUsuario;
    private String usuario;
    private String nombre;
    private String rol;

    public Usuario() {}

    public Usuario(int idUsuario, String usuario, String nombre, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.rol = rol;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}