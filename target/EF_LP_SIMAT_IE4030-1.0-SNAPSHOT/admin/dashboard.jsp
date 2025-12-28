<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ef_lp_simat_ie4030.model.Usuario"%>
<%
    Usuario u = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Dashboard Admin | SIMAT</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/app.css">
         <style>
            
        .menu-admin {
            display: flex;
            align-items: center;
            gap: 4px;            
            flex-wrap: nowrap;     
        }

        .menu-admin a {
            white-space: nowrap;  
        }
        .menu-admin .btn {
            padding: 6px 10px;      
            font-size: 13px;       
            line-height: 1.2;
            border-radius: 6px;     
            white-space: nowrap;
        }
        </style>
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
                    <a class="primary" href="<%=request.getContextPath()%>/admin/dashboard.jsp">Dashboard</a>
                    <a class="danger" href="<%=request.getContextPath()%>/logout">Salir</a>
                </div>
            </div>

            <div class="card">
                <h2 class="title">Dashboard ADMIN</h2>
                <p class="subtitle">Hola, <b><%=u.getNombre()%></b>. Panel de control del sistema.</p>

                <div class="menu-admin">
                <a class="btn primary" href="#">👤 Usuarios y Roles</a>
                <a class="btn primary" href="<%=request.getContextPath()%>/admin/alumnos">📋 Ver Alumnos</a>
                <a class="btn primary" href="<%=request.getContextPath()%>/admin/alumnos/nuevo">👨‍🎓 Agregar Alumno</a>


                <a class="btn primary" href="<%=request.getContextPath()%>/admin/docentes">👩‍🏫 Ver Docentes</a>
                <a class="btn primary" href="<%=request.getContextPath()%>/admin/docentes/nuevo">➕ Agregar Docente</a>

                <a class="btn primary" href="<%=request.getContextPath()%>/admin/cursos">📚 Ver Cursos</a>
                <a class="btn primary" href="<%=request.getContextPath()%>/admin/cursos/nuevo">➕ Agregar Curso</a>


                <a class="btn primary" href="#">⚙️ Parámetros</a>
                </div>
                <p class="hint">Botones placeholder (puedes conectarlos después).</p>
            </div>

        </div>
    </body>
</html>
