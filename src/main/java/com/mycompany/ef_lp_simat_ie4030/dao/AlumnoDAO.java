package com.mycompany.ef_lp_simat_ie4030.dao;

import com.mycompany.ef_lp_simat_ie4030.model.Alumno;
import java.util.List;

public interface AlumnoDAO {
    int insert(Alumno a);
    boolean existsByDni(String dni);
    List<Alumno> listAll();
}
