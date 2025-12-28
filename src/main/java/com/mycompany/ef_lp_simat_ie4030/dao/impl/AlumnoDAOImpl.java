package com.mycompany.ef_lp_simat_ie4030.dao.impl;

import com.mycompany.ef_lp_simat_ie4030.config.DBConnection;
import com.mycompany.ef_lp_simat_ie4030.dao.AlumnoDAO;
import com.mycompany.ef_lp_simat_ie4030.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public int insert(Alumno a) {
        String sql = "INSERT INTO tb_alumno (dni, apellidos, nombres, grado, seccion, estado) VALUES (?,?,?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getDni());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getNombres());
            ps.setString(4, a.getGrado());
            ps.setString(5, a.getSeccion());
            ps.setInt(6, a.getEstado());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        a.setIdAlumno(rs.getInt(1));
                    }
                }
            }
            return rows;
        } catch (Exception e) {
            System.out.println("ERROR AlumnoDAO.insert: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean existsByDni(String dni) {
        String sql = "SELECT 1 FROM tb_alumno WHERE dni = ? LIMIT 1";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("ERROR AlumnoDAO.existsByDni: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Alumno> listAll() {
        String sql = "SELECT id_alumno, dni, apellidos, nombres, grado, seccion, estado FROM tb_alumno ORDER BY id_alumno DESC";
        List<Alumno> list = new ArrayList<>();
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("id_alumno"));
                a.setDni(rs.getString("dni"));
                a.setApellidos(rs.getString("apellidos"));
                a.setNombres(rs.getString("nombres"));
                a.setGrado(rs.getString("grado"));
                a.setSeccion(rs.getString("seccion"));
                a.setEstado(rs.getInt("estado"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("ERROR AlumnoDAO.listAll: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
