<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Curso"%>
<%
  Usuario u = (Usuario) session.getAttribute("usuario");
  String msg = request.getParameter("msg");
  List<Curso> docentes = (List<Curso>) request.getAttribute("cursos");

  String back = "/admin/dashboard.jsp";
  String nuevo = "/admin/cursos/nuevo";
  if (u != null && "ADMINISTRATIVO".equals(u.getRol())) {
    back = "/administrativo/dashboard.jsp";
    nuevo = "/administrativo/cursos/nuevo";
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Cursos | SIMAT</title>
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
      <a class="primary" href="<%=request.getContextPath()%><%=nuevo%>">➕ Nuevo Curso</a>
      <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
    </div>
  </div>

  <div class="card">
    <h2 class="title">Mantenimiento de Cursos</h2>

    <table class="table">
      <thead>
        <tr>
          <th>Código</th>
          <th>Nombre</th>
          <th>Créditos</th>
        </tr>
      </thead>
      <tbody>
      <%
        List<Curso> lista = (List<Curso>) request.getAttribute("listaCursos");
        if (lista != null) {
          for (Curso c : lista) {
      %>
        <tr>
          <td><%=c.getCodigo()%></td>
          <td><%=c.getNombre()%></td>
          <td><%=c.getCreditos()%></td>
        </tr>
      <% } } %>
      </tbody>
    </table>

  </div>

</div>
</body>
</html>

