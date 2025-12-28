/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ef_lp_simat_ie4030.dao;

import com.mycompany.ef_lp_simat_ie4030.model.Docente;
import java.util.List;
/**
 *
 * @author carlo
 */
public interface DocenteDAO {
    int insert(Docente d);
    boolean existsByDni(String dni);
    List<Docente> listAll();
}
