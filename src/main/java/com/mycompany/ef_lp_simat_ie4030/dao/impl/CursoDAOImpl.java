package com.mycompany.ef_lp_simat_ie4030.dao.impl;

import com.mycompany.ef_lp_simat_ie4030.config.DBConnection;
import com.mycompany.ef_lp_simat_ie4030.dao.CursoDAO;
import com.mycompany.ef_lp_simat_ie4030.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImpl implements CursoDAO {

    @Override
    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_curso WHERE estado = 1";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("id_curso"));
                c.setCodigo(rs.getString("codigo"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setCreditos(rs.getInt("creditos"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void insertar(Curso c) {
        String sql = "INSERT INTO tb_curso(codigo,nombre,descripcion,creditos) VALUES (?,?,?,?)";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getDescripcion());
            ps.setInt(4, c.getCreditos());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
