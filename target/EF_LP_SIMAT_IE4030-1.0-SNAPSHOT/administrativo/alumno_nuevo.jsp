<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
  String msg = request.getParameter("msg");
  String volver = "/administrativo/dashboard.jsp";
  String crear = "/administrativo/alumnos/crear";

  if (u != null && "ADMIN".equals(u.getRol())) {
    volver = "/admin/dashboard.jsp";
    crear = "/admin/alumnos/crear";
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Nuevo Alumno | SIMAT</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
</head>
<body>
<div class="container">

  <div class="topbar">
    <div class="brand">
      <div class="logo"></div>
      <div>
        <h1>SIMAT IE4030</h1>
        <small>Panel • <span class="badge"><%=u!=null ? u.getRol() : ""%></span></small>
      </div>
    </div>
    <div class="nav">
      <a class="ghost" href="<%=request.getContextPath()%><%=volver%>">⬅ Volver</a>
      <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
    </div>
  </div>

  <div class="card">
    <h2 class="title">Registrar Alumno</h2>
    <p class="subtitle">Completa los datos y guarda.</p>

    <% if ("ok".equals(msg)) { %>
      <div class="alert ok">Alumno registrado correctamente.</div>
    <% } else if ("err".equals(msg)) { %>
      <div class="alert err">Ocurrió un error al guardar. Revisa los datos.</div>
    <% } else if ("val".equals(msg)) { %>
      <div class="alert info">Completa al menos DNI, Apellidos y Nombres.</div>
    <% } %>

    <form class="form" method="post" action="<%=request.getContextPath()%><%=crear%>">
      <div>
        <label class="label">DNI</label>
        <input class="input" type="text" name="dni" maxlength="12" required/>
      </div>
      <div>
        <label class="label">Apellidos</label>
        <input class="input" type="text" name="apellidos" required/>
      </div>
      <div>
        <label class="label">Nombres</label>
        <input class="input" type="text" name="nombres" required/>
      </div>
      <div class="row">
        <div>
          <label class="label">Grado</label>
          <input class="input" type="text" name="grado" placeholder="Ej: 3°"/>
        </div>
        <div>
          <label class="label">Sección</label>
          <input class="input" type="text" name="seccion" placeholder="Ej: A"/>
        </div>
      </div>

      <div class="row">
        <button class="btn primary" type="submit">Guardar</button>
        <a class="btn ghost" href="<%=request.getContextPath()%><%=volver%>">Cancelar</a>
      </div>
    </form>

  </div>

</div>
</body>
</html>
