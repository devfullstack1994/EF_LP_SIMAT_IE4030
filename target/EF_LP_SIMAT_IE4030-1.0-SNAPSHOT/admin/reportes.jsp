<%-- 
    Document   : reportes
    Created on : 27 dic. 2025, 21:15:26
    Author     : carlo
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
  if(u==null){ response.sendRedirect(request.getContextPath()+"/login.jsp"); return; }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Reportes | SIMAT</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
  <style>
     .row1 {
    display: flex;
    justify-content: center;
    gap: 20px;               
    }

    .card1 {
        text-align: center;   
        width: 335px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="card">
    <h2 class="title">Reportes</h2>
    <p class="subtitle">Usted puede exportar los reportes de Alumnos y Docentes por las siguientes opciones:</p>

    <div class="row1">
      <div class="card card1">
        <h3 class="title">Alumnos</h3>
      <!-- <a class="btn primary" href="<%=request.getContextPath()%>/reportes/export?tipo=alumnos&fmt=pdf">Exportar PDF</a>comment -->
        <a class="btn primary" href="<%=request.getContextPath()%>/reportes/export?tipo=alumnos&fmt=xlsx">📊 Exportar Excel</a>
      </div>

      <div class="card card1">
        <h3 class="title">Docentes</h3>
      <!--  <a class="btn primary" href="<%=request.getContextPath()%>/reportes/export?tipo=docentes&fmt=pdf">Exportar PDF</a>comment -->
        <a class="btn primary" href="<%=request.getContextPath()%>/reportes/export?tipo=docentes&fmt=xlsx">📊 Exportar Excel</a>
      </div>
    </div>

  </div>
</div>
</body>
</html>
 
