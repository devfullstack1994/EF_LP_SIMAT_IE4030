<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Dashboard Docente | SIMAT</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
</head>
<body>
<div class="container">

  <div class="topbar">
    <div class="brand">
      <div class="logo"></div>
      <div>
        <h1>SIMAT IE4030</h1>
        <small>Panel • <span class="badge"><%=u.getRol()%></span></small>
      </div>
    </div>
    <div class="nav">
      <a class="primary" href="<%=request.getContextPath()%>/docente/dashboard.jsp">Dashboard</a>
      <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
    </div>
  </div>

  <div class="card">
    <h2 class="title">Dashboard DOCENTE</h2>
    <p class="subtitle">Hola, <b><%=u.getNombre()%></b>. Aquí gestionas tus cursos.</p>

    <a class="btn primary" href="#">📚 Mis Cursos</a>
    <a class="btn ghost" href="#">📝 Registro de notas</a>
    <p class="hint">Botones placeholder (puedes conectarlos después).</p>
  </div>

</div>
</body>
</html>
