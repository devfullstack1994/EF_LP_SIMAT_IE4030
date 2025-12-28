/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ef_lp_simat_ie4030.controller;

import com.mycompany.ef_lp_simat_ie4030.dao.DocenteDAO;
import com.mycompany.ef_lp_simat_ie4030.dao.impl.DocenteDAOImpl;
import com.mycompany.ef_lp_simat_ie4030.model.Docente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
/**
 *
 * @author carlo
 */
@WebServlet(name = "DocenteServlet", urlPatterns = {
        "/administrativo/docentes",
        "/administrativo/docentes/nuevo",
        "/administrativo/docentes/crear",
        "/admin/docentes",
        "/admin/docentes/nuevo",
        "/admin/docentes/crear"
})
public class DocenteServlet extends HttpServlet {

    private final DocenteDAO docenteDAO = new DocenteDAOImpl();

    private String baseByRole(HttpServletRequest request) {
        HttpSession ses = request.getSession(false);
        if (ses != null) {
            Object o = ses.getAttribute("usuario");
            if (o instanceof com.mycompany.ef_lp_simat_ie4030.model.Usuario) {
                String rol = ((com.mycompany.ef_lp_simat_ie4030.model.Usuario) o).getRol();
                if ("ADMIN".equals(rol)) return "/admin";
                if ("ADMINISTRATIVO".equals(rol)) return "/administrativo";
            }
        }
        // fallback: por path
        String sp = request.getServletPath();
        return sp.startsWith("/admin") ? "/admin" : "/administrativo";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sp = request.getServletPath();
        String ctx = request.getContextPath();

        if (sp.endsWith("/nuevo")) {
            // puedes usar uno solo (recomendado)
            request.getRequestDispatcher("/administrativo/docente_nuevo.jsp").forward(request, response);

            return;
        }

        if (sp.equals("/admin/docentes") || sp.equals("/administrativo/docentes")) {
            request.setAttribute("docentes", docenteDAO.listAll());
            request.getRequestDispatcher("/admin/docente_list.jsp").forward(request, response);

            return;
        }

        // fallback
        String base = baseByRole(request);
        response.sendRedirect(ctx + base + "/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        String sp = request.getServletPath();
        String ctx = request.getContextPath();
        String base = baseByRole(request); // <-- clave

        if (sp.endsWith("/crear")) {

            String dni = request.getParameter("dni");
            String apellidos = request.getParameter("apellidos");
            String nombres = request.getParameter("nombres");
            String especialidad = request.getParameter("especialidad");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");

            if (dni == null || dni.isEmpty() ||
                apellidos == null || apellidos.isEmpty() ||
                nombres == null || nombres.isEmpty()) {
                response.sendRedirect(ctx + base + "/docentes/nuevo?msg=val");
                return;
            }

            if (docenteDAO.existsByDni(dni)) {
                response.sendRedirect(ctx + base + "/docentes/nuevo?msg=dni");
                return;
            }

            Docente d = new Docente(dni, apellidos, nombres, especialidad, telefono, email, 1);
            int rows = docenteDAO.insert(d);

            if (rows > 0) {
                response.sendRedirect(ctx + base + "/docentes?msg=ok");
            } else {
                response.sendRedirect(ctx + base + "/docentes/nuevo?msg=err");
            }

        } else {
            response.sendRedirect(ctx + base + "/dashboard.jsp");
        }
    }
}
