<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Login | SIMAT IE4030</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
</head>
<body>
  <div class="container">

    <div class="topbar">
      <div class="brand">
        <div class="logo"></div>
        <div>
          <h1>SIMAT IE4030</h1>
          <small>Sistema de Matrícula • Acceso por perfiles</small>
        </div>
      </div>
      <div class="nav">
        
      </div>
    </div>

    <div class="card">
      <h2 class="title">Bienvenido 👋</h2>
      <p class="subtitle">Inicia sesión para continuar con tu perfil de matrícula.</p>

      <% if ("err".equals(msg)) { %>
        <div class="alert err">Usuario o clave incorrectos.</div>
      <% } else if ("out".equals(msg)) { %>
        <div class="alert ok">Sesión cerrada correctamente.</div>
      <% } else if ("rol".equals(msg)) { %>
        <div class="alert info">No tienes permiso para acceder a esa sección.</div>
      <% } %>

      <form class="form" method="post" action="<%=request.getContextPath()%>/login">
        <div>
          <label class="label">Usuario</label>
          <input class="input" type="text" name="usuario" placeholder="Ej: est1" required/>
        </div>

        <div>
          <label class="label">Clave</label>
          <input class="input" type="password" name="clave" placeholder="••••" required/>
        </div>

        <div class="row">
          <button class="btn primary" type="submit">Ingresar</button>
          <span class="hint">Usa usuarios de prueba (abajo).</span>
        </div>
      </form>

      <p class="hint">
        <b>Usuarios:</b> est1/1234 • doc1/1234 • adm1/1234 • pad1/1234 • root/1234
      </p>
    </div>

  </div>
</body>
</html>
