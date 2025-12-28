<%-- 
    Document   : docentes_list
    Created on : 27 dic. 2025, 16:38:10
    Author     : carlo
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Docente"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
  String msg = request.getParameter("msg");
  List<Docente> docentes = (List<Docente>) request.getAttribute("docentes");

  String back = "/admin/dashboard.jsp";
  String nuevo = "/admin/docentes/nuevo";
  if (u != null && "ADMINISTRATIVO".equals(u.getRol())) {
    back = "/administrativo/dashboard.jsp";
    nuevo = "/administrativo/docentes/nuevo";
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Docentes | SIMAT</title>
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
      <a class="primary" href="<%=request.getContextPath()%><%=nuevo%>">➕ Nuevo Docente</a>
      <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
    </div>
  </div>

  <div class="card">
    <h2 class="title">Listado de Docentes</h2>
    <p class="subtitle">Registros recientes en el sistema.</p>

    <% if ("ok".equals(msg)) { %>
      <div class="alert ok">Docente registrado correctamente.</div>
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
          <th>Especialidad</th>
          <th>Teléfono</th>
          <th>Email</th>
          <th>Estado</th>
        </tr>
      </thead>

      <tbody>
      <% if (docentes != null && !docentes.isEmpty()) {
           for (Docente d : docentes) { %>
        <tr>
          <td><%=d.getIdDocente()%></td>
          <td><%=d.getDni()%></td>
          <td><%=d.getApellidos()%></td>
          <td><%=d.getNombres()%></td>
          <td><%= d.getEspecialidad()==null ? "" : d.getEspecialidad() %></td>
          <td><%= d.getTelefono()==null ? "" : d.getTelefono() %></td>
          <td><%= d.getEmail()==null ? "" : d.getEmail() %></td>
          <td><%= d.getEstado()==1 ? "Activo" : "Inactivo" %></td>
        </tr>
      <%   }
         } else { %>
        <tr>
          <td colspan="8"><em>No hay docentes registrados.</em></td>
        </tr>
      <% } %>
      </tbody>
    </table>

  </div>

</div>
</body>
</html>
