<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Dashboard Padres | SIMAT</title>
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
      <a class="primary" href="<%=request.getContextPath()%>/padres/dashboard.jsp">Dashboard</a>
      <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
    </div>
  </div>

  <div class="card">
    <h2 class="title">Dashboard PADRES</h2>
    <p class="subtitle">Hola, <b><%=u.getNombre()%></b>. Aquí verificas el estado de matrícula.</p>

    <a class="btn primary" href="#">✅ Estado de Matrícula</a>
    <a class="btn ghost" href="#">📢 Comunicados</a>
    <p class="hint">Botones placeholder (puedes conectarlos después).</p>
  </div>

</div>
</body>
</html>
