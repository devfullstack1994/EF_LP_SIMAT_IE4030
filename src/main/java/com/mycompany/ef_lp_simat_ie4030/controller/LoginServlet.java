package com.mycompany.ef_lp_simat_ie4030.controller;

import com.mycompany.ef_lp_simat_ie4030.dao.UsuarioDAO;
import com.mycompany.ef_lp_simat_ie4030.dao.impl.UsuarioDAOImpl;
import com.mycompany.ef_lp_simat_ie4030.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name="LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String clave   = request.getParameter("clave");

        Usuario u = usuarioDAO.login(usuario, clave);
        String ctx = request.getContextPath();

        if (u == null) {
            response.sendRedirect(ctx + "/login.jsp?msg=err");
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("usuario", u);

        // Redirección por rol
        switch (u.getRol()) {
            case "ESTUDIANTE":
                response.sendRedirect(ctx + "/estudiante/dashboard.jsp");
                break;
            case "DOCENTE":
                response.sendRedirect(ctx + "/docente/dashboard.jsp");
                break;
            case "ADMINISTRATIVO":
                response.sendRedirect(ctx + "/administrativo/dashboard.jsp");
                break;
            case "PADRES":
                response.sendRedirect(ctx + "/padres/dashboard.jsp");
                break;
            case "ADMIN":
                response.sendRedirect(ctx + "/admin/dashboard.jsp");
                break;
            default:
                session.invalidate();
                response.sendRedirect(ctx + "/login.jsp?msg=rol");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
