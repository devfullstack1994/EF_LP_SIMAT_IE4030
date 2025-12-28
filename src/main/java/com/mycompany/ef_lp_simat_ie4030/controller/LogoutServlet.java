package com.mycompany.ef_lp_simat_ie4030.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name="LogoutServlet", urlPatterns={"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession s = request.getSession(false);
        if (s != null) s.invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp?msg=out");
    }
}
