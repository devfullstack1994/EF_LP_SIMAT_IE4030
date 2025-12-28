/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ef_lp_simat_ie4030.model;

import java.io.Serializable;
/**
 *
 * @author carlo
 */
public class Docente implements Serializable {

    private int idDocente;
    private String dni;
    private String apellidos;
    private String nombres;
    private String especialidad;
    private String telefono;
    private String email;
    private int estado;

    public Docente() {}

    public Docente(String dni, String apellidos, String nombres,
                   String especialidad, String telefono, String email, int estado) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }

    public int getIdDocente() { return idDocente; }
    public void setIdDocente(int idDocente) { this.idDocente = idDocente; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}