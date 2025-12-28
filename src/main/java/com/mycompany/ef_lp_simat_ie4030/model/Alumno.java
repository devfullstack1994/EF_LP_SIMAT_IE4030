package com.mycompany.ef_lp_simat_ie4030.model;

import java.io.Serializable;

public class Alumno implements Serializable {

    private int idAlumno;
    private String dni;
    private String apellidos;
    private String nombres;
    private String grado;
    private String seccion;
    private int estado;

    public Alumno() {}

    public Alumno(String dni, String apellidos, String nombres, String grado, String seccion, int estado) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.grado = grado;
        this.seccion = seccion;
        this.estado = estado;
    }

    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }

    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}
