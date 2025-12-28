package com.mycompany.ef_lp_simat_ie4030.dao;

import com.mycompany.ef_lp_simat_ie4030.model.Usuario;

public interface UsuarioDAO {
    Usuario login(String usuario, String clave);
}

