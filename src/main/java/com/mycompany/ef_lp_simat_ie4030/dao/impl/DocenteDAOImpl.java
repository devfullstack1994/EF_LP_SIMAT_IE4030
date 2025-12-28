/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ef_lp_simat_ie4030.dao.impl;

import com.mycompany.ef_lp_simat_ie4030.config.DBConnection;
import com.mycompany.ef_lp_simat_ie4030.dao.DocenteDAO;
import com.mycompany.ef_lp_simat_ie4030.model.Docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author carlo
 */
public class DocenteDAOImpl implements DocenteDAO {

    @Override
    public int insert(Docente d) {
        String sql = "INSERT INTO tb_docente (dni, apellidos, nombres, especialidad, telefono, email, estado) " +
                     "VALUES (?,?,?,?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, d.getDni());
            ps.setString(2, d.getApellidos());
            ps.setString(3, d.getNombres());
            ps.setString(4, d.getEspecialidad());
            ps.setString(5, d.getTelefono());
            ps.setString(6, d.getEmail());
            ps.setInt(7, d.getEstado());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) d.setIdDocente(rs.getInt(1));
                }
            }
            return rows;

        } catch (Exception e) {
            System.out.println("ERROR DocenteDAO.insert: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean existsByDni(String dni) {
        String sql = "SELECT 1 FROM tb_docente WHERE dni = ? LIMIT 1";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("ERROR DocenteDAO.existsByDni: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Docente> listAll() {
        String sql = "SELECT id_docente, dni, apellidos, nombres, especialidad, telefono, email, estado " +
                     "FROM tb_docente ORDER BY id_docente DESC";
        List<Docente> list = new ArrayList<>();
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Docente d = new Docente();
                d.setIdDocente(rs.getInt("id_docente"));
                d.setDni(rs.getString("dni"));
                d.setApellidos(rs.getString("apellidos"));
                d.setNombres(rs.getString("nombres"));
                d.setEspecialidad(rs.getString("especialidad"));
                d.setTelefono(rs.getString("telefono"));
                d.setEmail(rs.getString("email"));
                d.setEstado(rs.getInt("estado"));
                list.add(d);
            }

        } catch (Exception e) {
            System.out.println("ERROR DocenteDAO.listAll: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
