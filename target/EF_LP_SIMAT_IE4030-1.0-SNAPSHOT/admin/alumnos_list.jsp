<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Alumno"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
  String msg = request.getParameter("msg");
  List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
  String back = "/admin/dashboard.jsp";
  if (u != null && "ADMINISTRATIVO".equals(u.getRol())) {
    back = "/administrativo/dashboard.jsp";
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Alumnos | SIMAT</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
</head>
<body>
<div class="container">

  <div class="topbar">
    <div class="brand">
      <div class="logo"></div>
      <div>
        <h1>SIMAT IE4030</h1>
        <small>Panel • <span class="badge"><%=u!=null?u.getRol():""%></span></small>
      </div>
    </div>
    <div class="nav">
      <a class="ghost" href="<%=request.getContextPath()%><%=back%>">⬅ Volver</a>
      <a class="primary" href="<%=request.getContextPath()%>/admin/alumnos/nuevo">➕ Nuevo Alumno</a>
      <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
    </div>
  </div>

  <div class="card">
    <h2 class="title">Listado de Alumnos</h2>
    <p class="subtitle">Registros recientes en el sistema.</p>

    <% if ("ok".equals(msg)) { %>
      <div class="alert ok">Alumno registrado correctamente.</div>
    <% } else if ("dni".equals(msg)) { %>
      <div class="alert info">El DNI ya existe. Intenta con otro.</div>
    <% } else if ("err".equals(msg)) { %>
      <div class="alert err">Ocurrió un error al procesar la solicitud.</div>
    <% } %>

    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>DNI</th>
          <th>Apellidos</th>
          <th>Nombres</th>
          <th>Grado</th>
          <th>Sección</th>
          <th>Estado</th>
        </tr>
      </thead>
      <tbody>
      <% if (alumnos != null && !alumnos.isEmpty()) {
           for (Alumno a : alumnos) { %>
        <tr>
          <td><%=a.getIdAlumno()%></td>
          <td><%=a.getDni()%></td>
          <td><%=a.getApellidos()%></td>
          <td><%=a.getNombres()%></td>
          <td><%=a.getGrado()%></td>
          <td><%=a.getSeccion()%></td>
          <td><%=a.getEstado()==1?"Activo":"Inactivo"%></td>
        </tr>
      <%   }
         } else { %>
        <tr>
          <td colspan="7"><em>No hay alumnos registrados.</em></td>
        </tr>
      <% } %>
      </tbody>
    </table>

  </div>

</div>
</body>
</html>
