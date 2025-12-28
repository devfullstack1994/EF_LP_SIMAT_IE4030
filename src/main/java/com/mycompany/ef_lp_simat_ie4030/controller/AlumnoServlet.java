package com.mycompany.ef_lp_simat_ie4030.controller;

import com.mycompany.ef_lp_simat_ie4030.dao.AlumnoDAO;
import com.mycompany.ef_lp_simat_ie4030.dao.impl.AlumnoDAOImpl;
import com.mycompany.ef_lp_simat_ie4030.model.Alumno;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AlumnoServlet", urlPatterns = {
        "/administrativo/alumnos",
        "/administrativo/alumnos/nuevo",
        "/administrativo/alumnos/crear",
        "/admin/alumnos",
        "/admin/alumnos/nuevo",
        "/admin/alumnos/crear"
})
public class AlumnoServlet extends HttpServlet {

    private final AlumnoDAO alumnoDAO = new AlumnoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sp = request.getServletPath();
        if (sp.endsWith("/nuevo")) {
            request.getRequestDispatcher("/administrativo/alumno_nuevo.jsp").forward(request, response);
            return;
        }

        if (sp.equals("/admin/alumnos") || sp.equals("/administrativo/alumnos")) {
            request.setAttribute("alumnos", alumnoDAO.listAll());
            request.getRequestDispatcher("/admin/alumnos_list.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/administrativo/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String sp = request.getServletPath();
        String ctx = request.getContextPath();
        if (sp.endsWith("/crear")) {
            String dni = request.getParameter("dni");
            String apellidos = request.getParameter("apellidos");
            String nombres = request.getParameter("nombres");
            String grado = request.getParameter("grado");
            String seccion = request.getParameter("seccion");

            if (dni == null || dni.isEmpty() || apellidos == null || apellidos.isEmpty() ||
                nombres == null || nombres.isEmpty()) {
                response.sendRedirect(ctx + "/administrativo/alumnos/nuevo?msg=val");
                return;
            }

            if (alumnoDAO.existsByDni(dni)) {
                response.sendRedirect(ctx + "/administrativo/alumnos/nuevo?msg=dni");
                return;
            }

            Alumno a = new Alumno(dni, apellidos, nombres, grado, seccion, 1);
            int rows = alumnoDAO.insert(a);
            if (rows > 0) {
                // Redirección según rol
                Object o = request.getSession(false) != null ? request.getSession(false).getAttribute("usuario") : null;
                String target = "/administrativo/dashboard.jsp"; // por defecto
                if (o instanceof com.mycompany.ef_lp_simat_ie4030.model.Usuario) {
                    String rol = ((com.mycompany.ef_lp_simat_ie4030.model.Usuario) o).getRol();
                    if ("ADMIN".equals(rol)) {
                        target = "/admin/dashboard.jsp";
                    } else if ("ADMINISTRATIVO".equals(rol)) {
                        target = "/administrativo/dashboard.jsp";
                    }
                }
                response.sendRedirect(ctx + target);
            } else {
                response.sendRedirect(ctx + "/administrativo/alumnos/nuevo?msg=err");
            }
        } else {
            response.sendRedirect(ctx + "/administrativo/dashboard.jsp");
        }
    }
}
