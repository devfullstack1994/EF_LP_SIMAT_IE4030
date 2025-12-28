<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
  String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Nuevo Curso | SIMAT</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
</head>
<body>
<div class="container">

  <!-- TOPBAR -->
  <div class="topbar">
    <div class="brand">
      <div class="logo"></div>
      <div>
        <h1>SIMAT IE4030</h1>
        <small>Panel • <span class="badge"><%=u.getRol()%></span></small>
      </div>
    </div>
    <div class="nav">
      <a class="ghost" href="<%=request.getContextPath()%>/administrativo/dashboard.jsp">⬅ Volver</a>
      <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
    </div>
  </div>

  <!-- CARD -->
  <div class="card">
    <h2 class="title">Registrar Curso</h2>
    <p class="subtitle">Completa los datos del curso.</p>

    <% if ("val".equals(msg)) { %>
      <div class="alert info">Completa todos los campos obligatorios.</div>
    <% } else if ("err".equals(msg)) { %>
      <div class="alert err">Ocurrió un error al registrar.</div>
    <% } %>

    <form class="form" method="post"
          action="<%=request.getContextPath()%>/admin/cursos/crear">

      <div>
        <label class="label">Código</label>
        <input class="input" type="text" name="codigo" required/>
      </div>

      <div>
        <label class="label">Nombre</label>
        <input class="input" type="text" name="nombre" required/>
      </div>

      <div>
        <label class="label">Descripción</label>
        <input class="input" type="text" name="descripcion"/>
      </div>

      <div>
        <label class="label">Créditos</label>
        <input class="input" type="number" name="creditos" min="1" required/>
      </div>

      <div class="row">
        <button class="btn primary" type="submit">Guardar</button>
        <a class="btn ghost" href="<%=request.getContextPath()%>/administrativo/dashboard.jsp">Cancelar</a>
      </div>
    </form>
  </div>

</div>
</body>
</html>
