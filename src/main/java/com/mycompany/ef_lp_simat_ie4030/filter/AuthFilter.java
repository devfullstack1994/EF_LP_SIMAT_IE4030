package com.mycompany.ef_lp_simat_ie4030.filter;

import com.mycompany.ef_lp_simat_ie4030.model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*","/administrativo/*","/docente/*","/estudiante/*","/padres/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        Usuario u = (session == null) ? null : (Usuario) session.getAttribute("usuario");

        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String path = request.getRequestURI(); // incluye el contextPath
        String role = u.getRol();

        boolean ok =
                (path.contains("/admin/") && "ADMIN".equals(role)) ||
                (path.contains("/administrativo/") && "ADMINISTRATIVO".equals(role)) ||
                (path.contains("/docente/") && "DOCENTE".equals(role)) ||
                (path.contains("/estudiante/") && "ESTUDIANTE".equals(role)) ||
                (path.contains("/padres/") && "PADRES".equals(role));

        if (!ok) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?msg=rol");
            return;
        }

        chain.doFilter(req, res);
    }
}
