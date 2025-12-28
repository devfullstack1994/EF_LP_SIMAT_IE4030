package com.mycompany.ef_lp_simat_ie4030.dao;

import com.mycompany.ef_lp_simat_ie4030.model.Curso;
import java.util.List;

public interface CursoDAO {

    List<Curso> listar();
    void insertar(Curso curso);

}
