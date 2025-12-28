package com.mycompany.ef_lp_simat_ie4030.dao.impl;

import com.mycompany.ef_lp_simat_ie4030.config.DBConnection;
import com.mycompany.ef_lp_simat_ie4030.dao.UsuarioDAO;
import com.mycompany.ef_lp_simat_ie4030.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario login(String usuario, String clave) {

        String sql = "SELECT id_usuario, usuario, nombre, rol " +
                     "FROM tb_usuario " +
                     "WHERE usuario=? AND clave=? AND estado=1";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("usuario"),
                        rs.getString("nombre"),
                        rs.getString("rol")
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR UsuarioDAO.login: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
