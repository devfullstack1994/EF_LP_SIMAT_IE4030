<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Curso"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Cursos | SIMAT</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
</head>
<body>

<div class="container">

  <div class="card">
    <h2 class="title">Mantenimiento de Cursos</h2>

    <a class="btn primary" href="<%=request.getContextPath()%>/administrativo/cursos/nuevo">
      ➕ Nuevo Curso
    </a>

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

