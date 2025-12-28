package com.mycompany.ef_lp_simat_ie4030.controller;

import com.mycompany.ef_lp_simat_ie4030.dao.CursoDAO;
import com.mycompany.ef_lp_simat_ie4030.dao.impl.CursoDAOImpl;
import com.mycompany.ef_lp_simat_ie4030.model.Curso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CursoServlet", urlPatterns = {
    "/administrativo/cursos",
    "/administrativo/cursos/nuevo",
    "/administrativo/cursos/crear",
    "/admin/cursos",
    "/admin/cursos/nuevo",
    "/admin/cursos/crear"
})
public class CursoServlet extends HttpServlet {

    private final CursoDAO dao = new CursoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String sp = req.getServletPath();

        // FORM NUEVO
        if (sp.endsWith("/nuevo")) {
            req.getRequestDispatcher("/administrativo/curso_nuevo.jsp")
               .forward(req, resp);
            return;
        }

        // LISTADO
        if (sp.equals("/admin/cursos") || sp.equals("/administrativo/cursos")) {
            List<Curso> lista = dao.listar();
            req.setAttribute("listaCursos", lista);
            req.getRequestDispatcher("/admin/curso_list.jsp")
               .forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/administrativo/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String sp = req.getServletPath();
        String ctx = req.getContextPath();

        if (sp.endsWith("/crear")) {
            Curso c = new Curso();
            c.setCodigo(req.getParameter("codigo"));
            c.setNombre(req.getParameter("nombre"));
            c.setDescripcion(req.getParameter("descripcion"));
            c.setCreditos(Integer.parseInt(req.getParameter("creditos")));

            dao.insertar(c);

            // vuelve al listado
            if (sp.startsWith("/admin")) {
                resp.sendRedirect(ctx + "/admin/cursos");
            } else {
                resp.sendRedirect(ctx + "/administrativo/cursos");
            }
            return;
        }

        resp.sendRedirect(ctx + "/administrativo/dashboard.jsp");
    }
}
